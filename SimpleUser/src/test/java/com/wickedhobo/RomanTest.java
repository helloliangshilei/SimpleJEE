package com.wickedhobo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/rest-servlet.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class RomanTest {
  
  @Test
  public void testRomans() throws Exception {
    Roman roman = new Roman();
    System.out.println("The Roman Numberal VIII(8) is " + roman.returnNumbersBackwards("VIII").toString());
    System.out.println("The Roman Numberal III(3) is " + roman.returnNumbersBackwards("III").toString());
    System.out.println("The Roman Numberal IV(4) is " + roman.returnNumbersBackwards("IV").toString());
    System.out.println("The Roman Numberal IIII(4) is " + roman.returnNumbersBackwards("IIII").toString());
    System.out.println("The Roman Numberal XIII(13) is " + roman.returnNumbersBackwards("XIII").toString());
    System.out.println("The Roman Numberal XVII(17) is " + roman.returnNumbersBackwards("XVII").toString());
    System.out.println("The Roman Numberal XL(40) is " + roman.returnNumbersBackwards("XL").toString());
    System.out.println("The Roman Numberal CVI(106) is " + roman.returnNumbersBackwards("CVI").toString());
    System.out.println("The Roman Numberal DCCLXXXIX(789) is " + roman.returnNumbersBackwards("DCCLXXXIX").toString());
    System.out.println("The Roman Numberal MV(1005) is " + roman.returnNumbersBackwards("MV").toString());
    System.out.println("The Roman Numberal MMCCCXLVI(2436) is " + roman.returnNumbersBackwards("MMCCCXLVI").toString());
    System.out.println("The Roman Numberal CMMMV(2905) is " + roman.returnNumbersBackwards("CMMMV").toString());
  }
}
