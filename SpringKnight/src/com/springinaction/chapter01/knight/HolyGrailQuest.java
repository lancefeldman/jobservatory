package com.springinaction.chapter01.knight;

import org.apache.log4j.Logger;


public class HolyGrailQuest implements Quest {
  public HolyGrailQuest() {}
  
  public Object embark() throws GrailNotFoundException {
    // do whatever it means to embark on a quest
	Logger song = Logger.getLogger(this.getClass());
	    
	song.info("Embarked on a quest.");
	  
    return new HolyGrail();
  }
}
