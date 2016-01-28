package cz.i.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cz.i.dao.ObyvatelDao;
import cz.i.dto.ObyvatelDto;
import cz.i.dto.ObyvatelSearchCriteriaDto;
import cz.i.dto.ObyvatelUpdateDto;
import cz.i.entity.Obyvatel;
import cz.i.validation.Validator;

/**
 * @author jan.hadas@i.cz
 */
public class ObyvatelService {

	@Autowired
	private ObyvatelDao obyvatelDao;

	@Autowired
	private Validator validator;

	public List<Obyvatel> find(ObyvatelSearchCriteriaDto criteria) {
		validator.verify(criteria);

    return obyvatelDao.find(criteria);
	}

  public Integer insert(ObyvatelDto dto) {
    return obyvatelDao.insert(dto);
  }

  public void update(ObyvatelUpdateDto dto) {
    validator.verify(dto);

    obyvatelDao.update(dto);
  }

  public void delete(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("id is null");
    }
    obyvatelDao.delete(id);
  }
}
