package com.time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.ZoneId;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

class AllZoneIDAvaible {

	public static void main(String[] args) {
        Set<String> allZoneID = ZoneId.getAvailableZoneIds();
        TreeSet<String> ts = new TreeSet<>(allZoneID);//Set ordinato
        for (String ids : ts) {
            System.out.println(ids);
        }
        System.out.println();
        zoneIdSavingFile();
    }
	
	private static void zoneIdSavingFile(){
		try {
			FileOutputStream fos = new FileOutputStream(new File("IDtimeZone.txt"));
			PrintStream ps = new PrintStream(fos);
			String[] zoneId = TimeZone.getAvailableIDs(); 
			for(String i : zoneId){
				ps.println("* " + i);
			}
		} 
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
	}

}
