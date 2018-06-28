/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public abstract class Predmet {
  

  private final StringProperty nazev;
  private final IntegerProperty pocet;
  private final ListProperty<Zakaznik> vypujcky;

  public void vypujc(Zakaznik zak){
    getVypujcky().add(zak);
  }
  
  public void vrat(Zakaznik zak){
    getVypujcky().remove(zak);
  }
  
  public ObservableList getVypujcky() {
    return vypujcky.get();
  }

  public void setVypujcky(ObservableList value) {
    vypujcky.set(value);
  }

  public ListProperty vypujckyProperty() {
    return vypujcky;
  }
  
  public int getPocet() {
    return pocet.get();
  }

  public void setPocet(int value) {
    pocet.set(value);
  }

  public IntegerProperty pocetProperty() {
    return pocet;
  }
  
  public String getNazev() {
    return nazev.get();
  }

  public void setNazev(String value) {
    nazev.set(value);
  }

  public StringProperty nazevProperty() {
    return nazev;
  }
  
  
  public Predmet(String nazev, int pocet){ 
    this.nazev = new SimpleStringProperty(nazev);
    this.pocet = new SimpleIntegerProperty(pocet);
    ObservableList<Zakaznik> oL = FXCollections.observableArrayList();
    this.vypujcky = new SimpleListProperty<Zakaznik>(oL);
  }
  

  public Predmet(String nazev, int pocet, List vypujcky) { 
    this(nazev, pocet);
    this.vypujcky.addAll(vypujcky);
  }
  
 public abstract String asString();
 public abstract PredmetSer zeser();
}

//TODO abstraktni metoda toString, serializace