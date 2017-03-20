import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
* Semester:         CS367 Spring 2017
* PROJECT:          p3
* FILE:             ThesaurusRecord.java
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
 * The ThesaurusRecord class is the child class of Record to be used when merging thesaurus data.
 * The word field is the entry in the thesaurus, syn is the list of all associated synonyms.
 */

public class ThesaurusRecord extends Record{
	List<String> syn = new ArrayList<String>();			//list to hold all of the synonyms for the current keyword
	String word;
	
	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public ThesaurusRecord(int numFiles) {
	super(numFiles);
	clear();
    }

    /**
	 * This Comparator should simply behave like the default (lexicographic) compareTo() method
	 * for Strings, applied to the portions of the FileLines' Strings up to the ":"
	 * The getComparator() method of the ThesaurusRecord class will simply return an
	 * instance of this class.
	 * 
	 * @return 0 if keywords not equal
	 * @return 1 if keywords equal
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String temp1Str = l1.getString();
			String temp2Str = l2.getString();
			
			String[] temp1 = temp1Str.split(":");
			String[] temp2 = temp2Str.split(":");
			
			if(temp1[0].equals(temp2[0]))
					return 0;
			if(temp1[0].charAt(0) > temp2[0].charAt(0))
				return 1;
			return -1;
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the ThesaurusLineComparator class.
	 */
    public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
    }
	
	/**
	 * This method should (1) set the word to null and (2) empty the list of synonyms.
	 */
    public void clear() {
    	syn = new ArrayList<String>();
    	word = "";
    }
	
	/**
	 * This method should parse the list of synonyms contained in the given FileLine and insert any
	 * which are not already found in this ThesaurusRecord's list of synonyms.
	 */
    public void join(FileLine w) {
    	String[] temp = w.getString().split(":");
    	word = temp[0];
    	String[] tempWords = temp[1].split(",");
    	
    	for(int i = 0; i < tempWords.length; i++){
    		if(!wordInSyn(tempWords[i]))
    			syn.add(tempWords[i]);
    	}
    }
    
	/**
	 * This helper method is here to check if the provided word is in
	 * the current list of synonyms
	 * @param word The word to look for
	 * @return returns true if word is found, otherwise returns false
	 */
    private boolean wordInSyn(String word){
    	for(int i = 0; i < syn.size(); i++){
    		if(word.equals(syn.get(i))){
    			return true;
    		}
    	}
    	return false;
    }
	
	/**
	 * Creates a string with following format
	 * @return 'word:syn1,syn2,syn3,etc'
	 */
    public String toString() {
    	String result = word + ":";
    	Collections.sort(syn);
    	
    	for(int i = 0; i < syn.size(); i++){
    		result = result.concat(syn.get(i) + ",");							//add all syns in alphabetical order separated by commas
    	}
    	
		return result.substring(0, result.length() - 1);				//return the line minus the final ','
	}
}
