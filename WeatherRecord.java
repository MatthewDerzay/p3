import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* Semester:         CS367 Spring 2017
* PROJECT:          p3
* FILE:             WeatherRecord.java
* 
* TEAM:    20.5
* Authors: 
* Author1: Vincent Cunningham, vcunningham@wisc.edu, vcunningham, 001
* Author2: (name2,email2,netID2,lecture number2)
* 
* ---------------- OTHER ASSISTANCE CREDITS 
* Persons: Identify persons by name, relationship to you, and email. 
* Describe in detail the the ideas and help they provided. 
* 
* Online sources: avoid web searches to solve your problems, but if you do 
* search, be sure to include Web URLs and description of 
* of any information you find. 
////////////////////////////80 columns wide /////////////////////////////// */

/**
 * The WeatherRecord class is the child class of Record to be used when merging weather data. Station and Date
 * store the station and date associated with each weather reading that this object stores.
 * l stores the weather readings, in the same order as the files from which they came are indexed.
 */
public class WeatherRecord extends Record{
	List<String> readings = new ArrayList<String>();
	int[] readingIndexes;		//list for readings, list to track what file they came from
	int station;
	int date;
	
	/**
	 * Constructs a new WeatherRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public WeatherRecord(int numFiles) {
		super(numFiles);
		clear();
    }
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 * 
	 * @return 0 if station and date are not the same for both lines
	 * @return 1 if station and date are the same for both lines
	 */
    private class WeatherLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String temp1Str = l1.getString();
			String temp2Str = l2.getString();
			
			String[] temp1 = temp1Str.split(",");				//split to check without looking at following values
			String[] temp2 = temp2Str.split(",");
			
			if(temp1[0] == temp2[0]){
				if(temp1[1] == temp2[1]){
					return 0;
				}
				if(Integer.parseInt(temp1[1]) < Integer.parseInt(temp2[1]))
					return 1;
				return -1;
			}
			return 1;
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the WeatherLineComparator
	 * class.
	 */
    public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
    }
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
    public void clear() {
    	readings = new ArrayList<String>();
    	readingIndexes = new int[super.getNumFiles()];
    	station = 0;
    	date = 0;
    }

	/**
	 * This method should parse the String associated with the given FileLine to get the station, date, and reading
	 * contained therein. Then, in the data structure holding each reading, the entry with index equal to the parameter 
	 * FileLine's index should be set to the value of the reading. Also, so that
	 * this method will handle merging when this WeatherRecord is empty, the station and date associated with this
	 * WeatherRecord should be set to the station and date values which were similarly parsed.
	 */
    public void join(FileLine li) {
    	String[] temp = li.getString().split(",");
    	station = Integer.parseInt(temp[0]);
    	date = Integer.parseInt(temp[1]);
    	
    	for(int i = 2; i < temp.length; i++){						//add all readings to the readings list, and their file index to the index list
    		readings.add(temp[i]);
    		readingIndexes[li.getFileIterator().getIndex()] = li.getFileIterator().getIndex();
    	}
    }
	
	/**
	 * Creates a string with the following format:
	 * @return 'station,date,mod1,mod2,mod3,etc'
	 */
    public String toString() {
    	String result = station + "," + date + ",";

    	for(int i = 0; i < readingIndexes.length; i++){
    		if(readingIndexes[i] != i){							//if no value found for that index in this line then toss in a '-'
    			result = result.concat("-,");
    		}
    		else{													//otherwise add the reading to the line
    			result = result.concat(readings.get(i) + ",");
    		}
    	}
		return result.substring(0, result.length() - 1);			//return the line minus the final ','
    }
}
