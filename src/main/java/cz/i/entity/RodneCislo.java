package cz.i.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cz.i.common.Pohlavi;
import cz.i.common.StavRc;

/**
 * @author jan.hadas@i.cz
 */
@Entity
@Table(name = "rodne_cislo")
@NamedQueries(
    @NamedQuery(name = RodneCislo.RODNE_CISLO_PRIJMENI_QUERY,
        query = "select r from RodneCislo r where r.prijmeni = :prijmeni")
)
public class RodneCislo {

  public static final String RODNE_CISLO_PRIJMENI_QUERY = "RodneCislo.prijmeniQuery";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private String jmeno;

  @Column
  private String prijmeni;

  @Temporal(TemporalType.DATE)
  @Column(name = "datum_narozeni")
  private Date datumNarozeni;

  @Column(name = "datum_narozeni_text")
  private String datumNarozeniText;

  @Column(name = "rodne_cislo")
  private String rodneCislo;

  @Enumerated(EnumType.STRING)
  @Column
  private Pohlavi pohlavi;

  @Enumerated(EnumType.STRING)
  @Column(name = "stav_rc")
  private StavRc stavRc;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "posledni_zmena")
  private Date posledniZmena;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public StavRc getStavRc() {
    return stavRc;
  }

  public void setStavRc(StavRc stavRc) {
    this.stavRc = stavRc;
  }

  public Date getPosledniZmena() {
    return posledniZmena;
  }

  public void setPosledniZmena(Date posledniZmena) {
    this.posledniZmena = posledniZmena;
  }

  public Pohlavi getPohlavi() {
    return pohlavi;
  }

  public void setPohlavi(Pohlavi pohlavi) {
    this.pohlavi = pohlavi;
  }

  public String getDatumNarozeniText() {
    return datumNarozeniText;
  }

  public void setDatumNarozeniText(String datumNarozeniText) {
    this.datumNarozeniText = datumNarozeniText;
  }
}
