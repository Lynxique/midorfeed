/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.time.LocalDate;

/**
 *
 * @author User
 */
class Vypujcka {
    private String jmeno;
    private LocalDate lhutaOd, lhutaDo;

    public Vypujcka(String jmeno, LocalDate lhutaOd, LocalDate lhutaDo) {
        this.jmeno = jmeno;
        this.lhutaOd = lhutaOd;
        this.lhutaDo = lhutaDo;
    }
    
    public String getJmeno(){
        return jmeno;
    }
    
    public String asString(){
        return (jmeno + " | " + lhutaOd.toString() + " - " + lhutaOd.plusMonths(1).toString());
    }
}
