/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jcu.uai.knihovna;

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
    Knihovna knihovna = new Knihovna();
    System.out.println(knihovna.toString());
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
