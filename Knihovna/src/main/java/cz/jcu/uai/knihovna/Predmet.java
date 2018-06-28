/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class Predmet {
  

  private final String nazev;
  private int pocet;
  private final ArrayList<Zakaznik> vypujcky;

  public boolean vypujc(Zakaznik zak){
    if(getVypujckySize() >= getPocet()) {
      return false;
    }
    return vypujcky.add(zak);
  }
  
  public boolean vrat(Zakaznik zak){
    if(getVypujckySize() == 0) {
      return false;
    }
    return vypujcky.remove(zak);
  }
  
  public int getVypujckySize() {
    return vypujcky.size();
  }
  
  public int getPocet() {
    return pocet;
  }

  public void setPocet(int value) {
    pocet = value;
  }

  public Predmet(String nazev, int pocet){ 
    this.nazev = nazev;
    this.pocet = pocet;
    this.vypujcky = new ArrayList<>();
  }

  public Predmet(String nazev, int pocet, ArrayList vypujcky) { 
    this(nazev, pocet);
    this.vypujcky.addAll(vypujcky);
  }
 public abstract String asString();
  

  }
}