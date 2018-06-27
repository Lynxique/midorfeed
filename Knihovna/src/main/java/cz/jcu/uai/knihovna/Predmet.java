<<<<<<< HEAD

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
    
    public ObservableList getVypujcky() {
        return vypujcky.get();
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.util.List;

/**
 *
 * @author User
 */
public abstract class Predmet {
    private String nazev;
    private int pocet;
    private List<Zakaznik> vypujcky;
    
}
>>>>>>> 356da39f48d2030b53d0af5ea5fd8ed6ce973e01
