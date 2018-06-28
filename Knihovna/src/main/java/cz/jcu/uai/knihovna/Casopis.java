/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Uzivatel
 */
public class Casopis extends Predmet {

  private final StringProperty autor;
  private final IntegerProperty rok;
  private final IntegerProperty mesic;

  public int getMesic() {
    return mesic.get();
  }

  public void setMesic(int value) {
    mesic.set(value);
  }

  public IntegerProperty mesicProperty() {
    return mesic;
  }

  public int getRok() {
    return rok.get();
  }

  public void setRok(int value) {
    rok.set(value);
  }

  public IntegerProperty rokProperty() {
    return rok;
  }
  
  
  public String getAutor() {
    return autor.get();
  }

  public void setAutor(String value) {
    autor.set(value);
  }

  public StringProperty autorProperty() {
    return autor;
  }
  
  
  public Casopis(String nazev, int rok, int mesic, int pocet) {
    super(nazev, pocet);
    this.mesic = new SimpleIntegerProperty(mesic);
    this.rok = new SimpleIntegerProperty(rok);
    this.autor = new SimpleStringProperty(""+rok+" "+mesic);
  }
  
  public Casopis(String nazev, int rok, int mesic, int pocet, List vypujcky) {
    super(nazev, pocet, vypujcky);
    this.mesic = new SimpleIntegerProperty(mesic);
    this.rok = new SimpleIntegerProperty(rok);
    this.autor = new SimpleStringProperty(""+rok+" "+mesic);
  }
  
  @Override
  public String asString(){
    String vysl = "" + getAutor() + "/" + getNazev();
    return vysl;
  }

  @Override
  public CasopisSer zeser() {
    return new CasopisSer(this);
  }
}
