package cz.i.dao;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.i.common.Druh;
import cz.i.common.Platnost;
import cz.i.dto.ObyvatelDto;
import cz.i.dto.ObyvatelSearchCriteriaDto;
import cz.i.dto.ObyvatelUpdateDto;
import cz.i.entity.Obyvatel;
import cz.i.mapper.ObyvatelMapper;

/**
 * @author jan.hadas@i.cz
 */
@Repository
public class ObyvatelDao {

  @PersistenceContext
  private EntityManager entityManager;
  
  private ObyvatelMapper mapper = new ObyvatelMapper();

  public List<Obyvatel> find(ObyvatelSearchCriteriaDto criteriaDto) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Obyvatel> query = builder.createQuery(Obyvatel.class);
    Root<Obyvatel> obyvatel = query.from(Obyvatel.class);

    Predicate searchCriteria = builder.equal(obyvatel.get("platnost"), Platnost.P);

    if (isNotEmpty(criteriaDto.getJmeno())) {
      searchCriteria = builder.and(searchCriteria, builder.equal(obyvatel.get("jmeno"), criteriaDto.getJmeno()));
    }
    if (isNotEmpty(criteriaDto.getPrijmeni())) {
      searchCriteria = builder.and(searchCriteria, builder.equal(obyvatel.get("prijmeni"), criteriaDto.getPrijmeni()));
    }
    if(criteriaDto.getDatumNarozeni() != null) {
      searchCriteria = builder.and(searchCriteria, builder.equal(obyvatel.get("datum_narozeni"), criteriaDto.getDatumNarozeni()));
    }
    if(isNotEmpty(criteriaDto.getRodneCislo())) {
      searchCriteria = builder.and(searchCriteria, builder.equal(obyvatel.get("rodne_cislo"), criteriaDto.getRodneCislo()));
    }

    query.where(searchCriteria);
    return entityManager.createQuery(query).getResultList();
  }

  public Obyvatel get(int id) {
    return entityManager.find(Obyvatel.class, id);
  }

  @Transactional
  public void update(ObyvatelUpdateDto dto) {
    Obyvatel obyvatel = get(dto.getId());

    mapper.map(dto, obyvatel);
    obyvatel.setPosledniZmena(new Date());
  }
  
  @Transactional
	public Integer insert(ObyvatelDto dto) {
		Obyvatel obyvatel = mapper.map(dto);
    obyvatel.setPlatnost(Platnost.P);
    obyvatel.setDruh(Druh.O);
    obyvatel.setVerze(0);
    obyvatel.setAifoKontrola(UUID.randomUUID().toString());
    obyvatel.setPosledniZmena(new Date());

		entityManager.persist(obyvatel);
		entityManager.flush();

    return obyvatel.getId();
	}

  @Transactional
  public void delete(Integer id) {
    Obyvatel obyvatel = get(id);
    entityManager.remove(obyvatel);
  }
}
