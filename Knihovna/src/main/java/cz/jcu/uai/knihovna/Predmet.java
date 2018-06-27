/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public abstract class Predmet {
    private String nazev;
    private int pocet;
    private List<Zakaznik> vypujcky;
    
    public void vypujc(Zakaznik zak) {
        getVypujcky().add(zak);
    }
    
    public void vrat (Zakaznik zak) {
        getVypujcky().remove(zak);
    }
    
    public List getVypujcky() {
        return vypujcky;
    }
    
}
