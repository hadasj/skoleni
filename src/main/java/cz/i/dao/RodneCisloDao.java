package cz.i.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.i.entity.RodneCislo;

/**
 * @author jan.hadas@i.cz
 */
@Repository
public class RodneCisloDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public void insert(RodneCislo entity) {
    entity.setPosledniZmena(new Date());
    entityManager.persist(entity);
    entityManager.flush();
  }

  public List<RodneCislo> searchByPrijmeni(String prijmeni) {
    TypedQuery<RodneCislo> query = entityManager.createNamedQuery(RodneCislo.RODNE_CISLO_PRIJMENI_QUERY, RodneCislo.class);
    query.setParameter("prijmeni", prijmeni);
    return query.getResultList();
  }
}
