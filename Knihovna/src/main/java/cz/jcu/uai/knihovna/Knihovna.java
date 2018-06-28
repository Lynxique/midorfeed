/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Uzivatel
 */
public class Knihovna {
  
  private ArrayList<Predmet> predmety;

  public Knihovna() {
    predmety = nactiPredmety();
    
  }
  /**
   * metoda načítající předměty ze souboru
   * @return ArrayList<Predmet> s nactenymi hodnotami
   */ 
 private ArrayList<Predmet> nactiPredmety(){
   ArrayList<Predmet> result = new ArrayList<>();
   try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Katalog.ser"))){
     result = (ArrayList<Predmet>) ois.readObject();
   } catch(ClassNotFoundException | IOException ex){
     System.out.println("Nastala chyba při načítání katalogu");
   }
   return result;
 }
 
 /**
  * metoda ukládající List s předměty do souboru
  */
 public void ulozPredmety(){
   try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Katalog.ser") )){
     oos.writeObject(predmety);
   }catch(IOException ex){
     System.out.println("Nastala chyba při ukládání katalogu");
   }
 }
 
  /**
   *
   * @param predmet
   */
  public void pridatPredmet(Predmet predmet){
    if(predmet == null) {
      throw new NullPointerException();
    } else {
      predmety.add(predmet);
    }
 }
 
  /**
   *
   * @param predmet
   * @param dekrement
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  public void odebratPredmet(Predmet predmet, int dekrement) throws IllegalArgumentException, NullPointerException{
   if(dekrement < 1) {
     throw new IllegalArgumentException("Chyba v argumentu");
   }
   if(predmet == null) {
     throw new NullPointerException();
   }
   if(predmet.getPocet() < dekrement){
      throw new IllegalArgumentException("Nelze odebrat více než máme.");
   }
   if(predmet.getPocet() - predmet.getVypujckySize() < dekrement){
     throw new IllegalArgumentException("Nelze odebrat, jsou ještě vypůjčeny.");
   }
   predmet.setPocet(predmet.getPocet() - dekrement);
   if(predmet.getPocet() == 0){
     predmety.remove(predmet);
   }
 }
  
  /**
   * Přidá novou výpůjčku
   * @param predmet Předmět kterého se metoda týká
   * @param zakaznik Zákazník k přidání
   * @return jestli byl dostatek knih k vypůjčení
   * @throws NullPointerException pokud jsou argumenty null
   */
  public boolean vypujcit(Predmet predmet, Zakaznik zakaznik) throws NullPointerException{
    if(predmet == null || zakaznik == null) {
      throw new NullPointerException();
    }
    return predmet.vypujc(zakaznik);
  }
  
  public boolean vratit(Predmet predmet, Zakaznik zakaznik) throws NullPointerException {
    if(predmet == null || zakaznik == null) {
      throw new NullPointerException();
    }
    return predmet.vrat(zakaznik);
  }

  @Override
  public String toString(){
    String res = "";
    for(Predmet predmet : predmety) {
      res = res + predmet.toString();
    }
    return res;
  }
}
