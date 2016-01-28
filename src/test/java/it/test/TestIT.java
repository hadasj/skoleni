package it.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.sf.lightair.LightAirSpringRunner;
import net.sf.lightair.annotation.Setup;
import net.sf.lightair.internal.factory.Factory;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cz.i.common.Pohlavi;
import cz.i.dao.RodneCisloDao;
import cz.i.entity.RodneCislo;
import cz.i.service.GeneratorService;

/**
 * @author jan.hadas@i.cz
 */
@RunWith(LightAirSpringRunner.class)
@ContextConfiguration("classpath:spring/test-app-context.xml")
public class TestIT {

  @Autowired
  private GeneratorService generator;

  @Autowired
  private RodneCisloDao dao;

  @Test
  @Setup("../clean-all.xml")
  public void should_generate_100_rc() throws SQLException, DataSetException {
    Date datumNarozeni = new Date();
    //TODO: FIX generator service
    generator.generate(datumNarozeni, Pohlavi.F, 100);

    ITable rodneCislo = getTable("rodne_cislo");
    assertEquals(100, rodneCislo.getRowCount());
  }

  @Test //TODO: FIX setup XML
  @Setup.List({@Setup("../clean-all.xml"), @Setup})
  public void should_find_Light_Air() {
    List<RodneCislo> result = dao.searchByPrijmeni("Air");

    assertEquals("Should find 1 LightAir", 1, result.size());
    assertEquals("Light", result.get(0).getJmeno());
  }

  private ITable getTable(String tableName) throws DataSetException, SQLException {
    return Factory.getInstance().getConnectionFactory().createConnection(null, null).createDataSet().getTable(tableName);
  }

}
