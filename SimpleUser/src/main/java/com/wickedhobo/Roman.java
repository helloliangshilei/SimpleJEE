package com.wickedhobo;

import java.util.HashMap;

public class Roman {

	  private HashMap<String, Integer> romansMap = new HashMap<String, Integer>();

	  public Roman() {
	    romansMap.put("M", 1000);
	    romansMap.put("D", 500);
	    romansMap.put("C", 100);
	    romansMap.put("L", 50);
	    romansMap.put("X", 10);
	    romansMap.put("V", 5);
	    romansMap.put("I", 1);
	   //romansMap.put("", 0);
	  }
 
  /*
   * Notes for Ryan:
   * Understand java.util.hashmap (google search will give lots of good stuff here) 
   * Remember length/iteration starts at zero 
   * i++/i-- 
   * understand for loop logic: initialize, boolean logic/termination, increment
   * +=/-= syntax 
   * why toUpperCase 
   * Understand hashmap behavior
   */
  public Integer returnNumbersBackwards(String romansIn) {
    int lastPosition = 0;
    Integer runningValue = 0;

    for (int i = romansIn.length() - 1; i >= 0; i--) {
      char c = romansIn.toUpperCase().charAt(i);
      int charVal = romansMap.get(Character.toString(c));

      if (lastPosition <= charVal) {
        runningValue += charVal;
        lastPosition = charVal;
      }
      else {
        runningValue -= charVal;
        lastPosition = charVal;
      }
    }
    return runningValue;
  }

  // TODO: Fix the Zero problem below. Test for null.
  // TODO: Switch the string indexing from backwards to forwards and fix.
  public Integer returnNumbersForwards(String romansIn) {
    int lastPosition = 0;
    Integer runningValue = 0; // is this ok since there's no zero? Or should it
                              // basically be null, and no number assigned?
    							//A: the hashmap class allows for null value and the null key. 
    							//I added 'romansMap.put("", 0)' to the hashmap
    							//Also I think that runningValue = 0 is denoting that at LP=0 the running value is 0 
//so the problem with going forward is that LP^0 can either be a + or - value, where as going backwards the last roman numeral could only be positive
    try {
      for (int i=0; i <= romansIn.length()-1; i++) { // change this to the
                                                         // other direction, fix
                                                         // method to work.
        char c = romansIn.toUpperCase().charAt(i);
        System.out.println("C is: " + c);
        /*int charVal = romansMap.get(Character.toString(c));

        if (lastPosition <= charVal) {
          runningValue -= charVal;
          lastPosition = charVal;
        }
        else {
          runningValue += charVal;
          lastPosition = charVal;
        }*/
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return runningValue;
  }
}
