package cz.i.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author jan.hadas@i.cz
 */
public class ObyvatelSearchCriteriaDto {

  private String jmeno;

  @NotNull
  private String prijmeni;
  private Date datumNarozeni;
  private String rodneCislo;

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    this.jmeno = jmeno;
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    this.prijmeni = prijmeni;
  }

  public Date getDatumNarozeni() {
    return datumNarozeni;
  }

  public void setDatumNarozeni(Date datumNarozeni) {
    this.datumNarozeni = datumNarozeni;
  }

  public String getRodneCislo() {
    return rodneCislo;
  }

  public void setRodneCislo(String rodneCislo) {
    this.rodneCislo = rodneCislo;
  }
}
