/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.time.LocalDate;
import java.io.Serializable;
/**
 * Třída Zakaznik reprezentuje jednu výpůjčku,
 * obsahuje jméno a datum výpůjčky a metody k získání 
 * jména a výpůjční lhůty
 * @author User
 */
public class Zakaznik implements Serializable {
    private final String jmeno;
    private final LocalDate lhutaOd;

    /**
     * konstruktor Zakaznik
     * @param jmeno String jméno výpůjčitele
     * @param lhutaOd LocalDate datum výpůjčky
     */
    public Zakaznik (String jmeno, LocalDate lhutaOd) {
        this.jmeno = jmeno;
        this.lhutaOd = lhutaOd;
    }
    /**
     * metoda pro vyvolání jména výpujčitele,
     * použitá pro umožnění vyhledávání podle výpůjčitele
     * @return String jméno výpujčitele
     */
    public String getJmeno(){
        return jmeno;
    }
    /**
     * textová reprezentace výpůjčky
     * @return String "jméno | lhůta od - do"
     */
    public String asString(){
        return (jmeno + " | " + lhutaOd.toString() + " - " + lhutaOd.plusMonths(1).toString());
    }
}
  