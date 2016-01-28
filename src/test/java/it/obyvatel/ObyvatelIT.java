package it.obyvatel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.ValidationException;

import net.sf.lightair.LightAirSpringRunner;
import net.sf.lightair.annotation.Setup;

import net.sf.lightair.annotation.Verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cz.i.common.Pohlavi;
import cz.i.dto.ObyvatelDto;
import cz.i.dto.ObyvatelSearchCriteriaDto;
import cz.i.dto.ObyvatelUpdateDto;
import cz.i.entity.Obyvatel;
import cz.i.service.ObyvatelService;

/**
 * @author jan.hadas@i.cz
 */
@RunWith(LightAirSpringRunner.class)
@Setup.List({
    @Setup("/it/clean-all.xml"), @Setup
})
@ContextConfiguration("classpath:spring/test-app-context.xml")
public class ObyvatelIT {

  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
  private static final int OBYVATEL_ID = 1;

  @Autowired
  private ObyvatelService obyvatelService;

  @Test
  public void should_return_hugo() throws ParseException {
    ObyvatelSearchCriteriaDto dto = new ObyvatelSearchCriteriaDto();
    dto.setPrijmeni("Boss");

    List<Obyvatel> result = obyvatelService.find(dto);

    assertNotNull(result);
    assertEquals(1, result.size());
    Obyvatel obyvatel = result.get(0);

    assertEquals("Hugo", obyvatel.getJmeno());
    assertEquals("Boss", obyvatel.getPrijmeni());
    assertEquals("11.12.1996", FORMAT.format(obyvatel.getDatumNarozeni()));
    assertEquals(Pohlavi.M, obyvatel.getPohlavi());
    assertEquals("9612114369", obyvatel.getRodneCislo());
  }

  @Test
  public void should_throw_validation_exception() {
    ObyvatelSearchCriteriaDto dto = new ObyvatelSearchCriteriaDto();
    dto.setJmeno("Hugo");

    try {
      obyvatelService.find(dto);
      fail("Should throw validation exception.");
    }

    catch (ValidationException e) {
      assertEquals("prijmeni may not be null", e.getMessage());
    }
  }

  @Test
  @Verify
  public void should_update_hugo() {
    ObyvatelUpdateDto dto = new ObyvatelUpdateDto();
    dto.setId(OBYVATEL_ID);
    dto.setJmeno("Alfons");
    dto.setPrijmeni("Kos");

    obyvatelService.update(dto);
  }

  @Test
  @Verify
  public void should_insert_sarka() throws ParseException {
    ObyvatelDto dto = new ObyvatelDto();
    dto.setJmeno("Šárka");
    dto.setPrijmeni("Klemensová");
    dto.setPohlavi(Pohlavi.F);
    dto.setDatumNarozeni(FORMAT.parse("16.01.1989"));

    Integer insertedObyvatelId = obyvatelService.insert(dto);
    assertNotNull(insertedObyvatelId);
  }

  @Test
  @Verify("../clean-all.xml")
  public void should_delete_hugo() {
    obyvatelService.delete(OBYVATEL_ID);
  }
}
