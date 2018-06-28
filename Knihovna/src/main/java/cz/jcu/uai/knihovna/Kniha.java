/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Uzivatel
 */
public class Kniha extends Predmet {
  
  private final StringProperty autor;

  public String getAutor() {
    return autor.get();
  }

  public void setAutor(String value) {
    autor.set(value);
  }

  public StringProperty autorProperty() {
    return autor;
  }
  
  
  public Kniha(String autor, String nazev, int pocet, List vypujcky) {
    super(nazev, pocet, vypujcky); 
    this.autor = new SimpleStringProperty(autor);
  }
  public Kniha(String autor, String nazev, int pocet) {
    super(nazev, pocet);
    this.autor = new SimpleStringProperty(autor);
  }

  @Override
  public String asString() {
    return (getAutor() + ": " + getNazev());
  }

  @Override
  public KnihaSer zeser() {
    return new KnihaSer(this);
  }
  
}
