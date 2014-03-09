package com.wickedhobo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/rest-servlet.xml", "classpath:applicationContext.xml" })
@WebAppConfiguration
public class InsertionSortTest {
  
  /*
   * Need to finish this test for ryan
   */
  @Test
  public void testInsertionSort() throws Exception {
    SimpleInsertionSort sis = new SimpleInsertionSort();
    int[] testArray = {5,2,4,6,1,3};
    int[] test = sis.returnSorted(testArray);
    System.out.println(test);
  }
}
