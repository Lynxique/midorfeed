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
  
  public KnihovnaJUnitTest() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
  @Test
  public void hello(){
    Knihovna knihovna = new Knihovna();
    System.out.println("000" + knihovna.toString() + "000");
    //knihovna.pridatPredmet(new Kniha("auto", "nazve", 10));
    knihovna.vypujcit(knihovna.getPredmet(0), new Zakaznik("PEPIK", LocalDate.now()));
    System.out.println("000" + knihovna.toString() + "000");
    knihovna.ulozPredmety();
  }
}
