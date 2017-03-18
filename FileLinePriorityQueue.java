import java.util.Comparator;

/**
* Semester:         CS367 Spring 2017
* PROJECT:          p3
* FILE:             FileLinePriorityQueue.java
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
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
	private FileLine[] lines;
	private FileLine currMax;
	private int currPos;
	//TODO
	
    private Comparator<FileLine> cmp;
    private int maxSize;

    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		currPos = 0;

		lines = new FileLine[initialSize];
		//TODO
    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
		// TODO

		return null;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		if(isEmpty())
			lines[0] = fl;
		if(cmp.compare(fl, currMax) == 1){
			if(currPos == maxSize)
				currPos = 0;
			lines[currPos] = fl;
			++currPos;
		}
		else{
			
		}
		currMax = fl;
    }

    public boolean isEmpty() {
		for(int i = 0; i < lines.length; ++i){
			if(lines[i] != null)
				return false;
		}
		return true;
    }
}
