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
public class Casopis extends Predmet {

  private final String autor;

  public String getAutor() {
    return autor;
  }

  public Casopis(String nazev, int rok, int mesic, int pocet) {
    super(nazev, pocet);
    this.autor = "" + rok + " " + mesic;
  }
  
  public Casopis(String nazev, int rok, int mesic, int pocet, List vypujcky) {
    super(nazev, pocet, vypujcky);
    this.autor = "" + rok + " " + mesic;
  }
  
  @Override
  public String asString(){
    return (getAutor() + ": " + getNazev() + " " + (getPocet()-getVypujckySize()) + "/" + getPocet() + " " + vypujckyString());
  }

  @Override
  public String getVice() {
    return getAutor();
  }
 
}
