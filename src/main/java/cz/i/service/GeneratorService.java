package cz.i.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cz.i.common.Pohlavi;
import cz.i.common.StavRc;
import cz.i.dao.RodneCisloDao;
import cz.i.entity.RodneCislo;

/**
 * @author jan.hadas@i.cz
 */
public class GeneratorService {

  private static final DateFormat FORMAT = new SimpleDateFormat("yyMMdd");
  private static final int MAX_SERIE = 999;

  @Autowired
  private RodneCisloDao rodneCisloDao;

  public void generate(Date datumNarozeni, Pohlavi pohlavi, int count) {

    for (int serie = 1; serie <= count && serie <= MAX_SERIE; serie++) {
      RodneCislo rodneCislo = prepareRodneCisloEntity(datumNarozeni, pohlavi, serie);

      rodneCisloDao.insert(rodneCislo);
    }
  }

  private RodneCislo prepareRodneCisloEntity(Date datumNarozeni, Pohlavi pohlavi, int serie) {
    RodneCislo rodneCislo = new RodneCislo();
    rodneCislo.setDatumNarozeni(datumNarozeni);
    rodneCislo.setRodneCislo(computeRodneCislo(datumNarozeni, pohlavi, serie));
    rodneCislo.setStavRc(StavRc.V);
    return rodneCislo;
  }

  private String computeRodneCislo(Date datumNarozeni, Pohlavi pohlavi, int i) {
    String datumNarozeniString = FORMAT.format(datumNarozeni);

    if (pohlavi.equals(Pohlavi.F)) {
      datumNarozeniString = Integer.toString(Integer.parseInt(datumNarozeniString) + 5000);
    }

    String rc = datumNarozeniString + String.format("%03d", i);
    int modulo = Integer.parseInt(rc) % 11;
    rc += modulo;
    return rc;
  }
}
