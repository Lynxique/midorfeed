/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Uzivatel
 */
public class KnihovnaJUnitTest {
  

  public Knihovna toTest;

  @Before
  public void setUp() {
    toTest = new Knihovna();
  }
  
  @Test
  public void test1(){
    assertTrue("Není prázdná", toTest.getPredmety().isEmpty());
    toTest.pridatPredmet(new Kniha("Pekarek", "C#", 10));
    assertEquals("Chyba při přidání Knihy", toTest.getPredmet(0).getNazev(), "C#");
    assertEquals("Chyba při přidání Knihy", toTest.getPredmet(0).getVice(), "Pekarek");
    assertEquals("Chyba při přidání Knihy", toTest.getPredmet(0).getPocet(), 10);
    toTest.pridatPredmet(new Casopis("National", 1999, 12, 15));
    assertEquals("Chyba při přidání Časáku", toTest.getPredmet(1).getNazev(), "National");
    assertEquals("Chyba při přidání Časáku", toTest.getPredmet(1).getVice(), "1999 12");
    assertEquals("Chyba při přidání Časáku", toTest.getPredmet(1).getPocet(), 15);
    assertEquals("Nesedí počet", toTest.getPredmety().size(), 2);
    assertTrue(toTest.vypujcit(toTest.getPredmet(0), new Zakaznik("Peky", LocalDate.now())));
    assertEquals(toTest.getPredmet(0).getVypujckySize(), 1);
    try{
      toTest.odebratPredmet(toTest.getPredmet(0), 10);
      fail();
    } catch(IllegalArgumentException | NullPointerException ex) {
      //prošlo v klidu
    }
  }
  
  @After
  public void tearDown() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
}
