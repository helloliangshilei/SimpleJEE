package com.wickedhobo;

import java.util.HashMap;
import java.util.StringTokenizer;

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
  }
  
  public Integer returnNumbers(String romansIn) {
    int lastPosition = 0;
    Integer runningValue = 0; //is this ok since there's no zero? Or should it basically be null, and no number assigned?
    
    try {
      for(int i = romansIn.length() - 1; i >= 0 ; i--){
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
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return runningValue;
  }
}
