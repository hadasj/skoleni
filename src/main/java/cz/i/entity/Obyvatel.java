package cz.i.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cz.i.common.Druh;
import cz.i.common.Platnost;
import cz.i.common.Pohlavi;

/**
 * @author jan.hadas@i.cz
 */
@Entity
public class Obyvatel {

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

  @Column(name = "rodne_cislo")
  private String rodneCislo;

  @Enumerated(EnumType.STRING)
  @Column
  private Pohlavi pohlavi;

  @Enumerated(EnumType.STRING)
  @Column
  private Platnost platnost;

  @Enumerated(EnumType.STRING)
  @Column
  private Druh druh;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "posledni_zmena")
  private Date posledniZmena;

  @Column
  private Integer verze;

  @Column(name = "aifo_kontrola")
  private String aifoKontrola;

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

  public Pohlavi getPohlavi() {
    return pohlavi;
  }

  public void setPohlavi(Pohlavi pohlavi) {
    this.pohlavi = pohlavi;
  }

  public Date getPosledniZmena() {
    return posledniZmena;
  }

  public void setPosledniZmena(Date posledniZmena) {
    this.posledniZmena = posledniZmena;
  }

  public Platnost getPlatnost() {
    return platnost;
  }

  public void setPlatnost(Platnost platnost) {
    this.platnost = platnost;
  }

  public Druh getDruh() {
    return druh;
  }

  public void setDruh(Druh druh) {
    this.druh = druh;
  }

  public Integer getVerze() {
    return verze;
  }

  public void setVerze(Integer verze) {
    this.verze = verze;
  }

  public String getAifoKontrola() {
    return aifoKontrola;
  }

  public void setAifoKontrola(String aifoKontrola) {
    this.aifoKontrola = aifoKontrola;
  }
}
