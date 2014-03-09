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
}
