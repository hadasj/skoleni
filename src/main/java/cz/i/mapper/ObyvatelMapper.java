package cz.i.mapper;

import cz.i.dto.ObyvatelDto;
import cz.i.entity.Obyvatel;

/**
 * @author jan.hadas@i.cz
 */
public class ObyvatelMapper {

  public Obyvatel map(ObyvatelDto dto) {
    Obyvatel entity = new Obyvatel();
    map(dto, entity);
    return entity;
  }

  public void map(ObyvatelDto dto, Obyvatel entity) {
    if (dto.getJmeno() != null) {
      entity.setJmeno(dto.getJmeno());
    }
    if(dto.getPrijmeni() != null) {
      entity.setPrijmeni(dto.getPrijmeni());
    }
    if(dto.getDatumNarozeni() != null) {
      entity.setDatumNarozeni(dto.getDatumNarozeni());
    }
    if(dto.getPohlavi() != null) {
      entity.setPohlavi(dto.getPohlavi());
    }
    if (dto.getRodneCislo() != null) {
      entity.setRodneCislo(dto.getRodneCislo());
    }
  }
}
