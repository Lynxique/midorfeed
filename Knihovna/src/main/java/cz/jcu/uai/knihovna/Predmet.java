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
    private StringProperty nazev;
    private IntegerProperty pocet;
    private ListProperty<Zakaznik> vypujcky;
    
    public void vypujc(Zakaznik zak) {
        getVypujcky().add(zak);
    }
    
    public void vrat (Zakaznik zak) {
        getVypujcky().remove(zak);
    }
    
    public List getVypujcky() {
        return vypujcky;
    }

    public void setNazev(StringProperty nazev) {
        this.nazev = nazev;
    }

    public void setPocet(IntegerProperty pocet) {
        this.pocet = pocet;
    }

    public void setVypujcky(ListProperty<Zakaznik> vypujcky) {
        this.vypujcky = vypujcky;
    }

    
    
    public Predmet (String nazev, int pocet) {
        this.nazev = new SimpleStringProperty(nazev);
        this.pocet = new SimpleIntegerProperty(pocet);
        ObservableList<Zakaznik> oL = FXCollections.observableArrayList();
        this.vypujcky = new SimpleListProperty<Zakaznik>(oL);
        
    }
    
    public Predmet (String nazev, int pocet, List vypujcky) {
        this(nazev, pocet);
        this.vypujcky.addAll(vypujcky);
    }      
}
