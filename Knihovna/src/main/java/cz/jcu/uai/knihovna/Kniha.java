/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;


/**
 *
 * @author Uzivatel
 */
public class Kniha extends Predmet {
  
  private final String autor;

  public String getAutor() {
    return autor;
  }

  public Kniha(String autor, String nazev, int pocet, List vypujcky) {
    super(nazev, pocet, vypujcky); 
    this.autor = autor;
  }
  public Kniha(String autor, String nazev, int pocet) {
    super(nazev, pocet);
    this.autor = autor;
  }

  @Override
  public String asString() {
    return (getAutor() + ": " + getNazev() + " " + (getPocet()-getVypujckySize()) + "/" + getPocet() + " " + vypujckyString());
  }

  @Override
  public String getVice() {
    return autor;
  }

}
