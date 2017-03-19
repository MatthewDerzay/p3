import java.util.Comparator;

/**
* Semester:         CS367 Spring 2017
* PROJECT:          p3
* FILE:             FileLinePriorityQueue.java
* 
* TEAM:    20.5
* Authors: 
* Author1: Vincent Cunningham, vcunningham@wisc.edu, vcunningham, 001
* Author2: Matt Derzay, mderzay@wisc.edu, mderzay, 001
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
 * An implementation of the MinPriorityQueueADT interface. This implementation 
 * stores FileLine objects. 
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
	//Array of the file lines acting as a priority queue.
	private FileLine[] lines;
	//The current position of the minimum value in the queue.
	private int curMinPos = -1;
	//The comparator passed in in the constructor.
    private Comparator<FileLine> cmp;
    //The max size of the array/queue.
    private int maxSize;
    //The minimum value being removed from the queue.
    private FileLine removedFileLine;
    
    /**
     * This is the constructor of the priority queue to determine the initial 
     * size and the comparator used for the specific data passed in.
     * 
     * @param initialSize This is the size of the queue.
     * @param cmp This is the comparator used to compare the file lines.
     */
    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		lines = new FileLine[initialSize];
		
		//Debug Line
		System.out.println(maxSize);
    }

    /**
     * This method removes the minimum value file line from the priority queue 
     * and returns it.
     * 
     * @return The removed file line.
     */
    public FileLine removeMin() throws PriorityQueueEmptyException {
    	//Throw an exception if the priority queue is empty. 
		if(isEmpty()) {
			throw new PriorityQueueEmptyException();
		}
		
		//Put the minimum value into a temporary variable so it can be returned.
		removedFileLine = lines[curMinPos];
		
		//Debug Line
		System.out.println("This is the removed file line " + removedFileLine + " at position " + curMinPos);
		
		//Erase the minimum value from the queue.
		lines[curMinPos] = null;
		//Decrement the minimum value to the new minimum value's position.
		--curMinPos;
		
		return removedFileLine;
    }

    /**
     * This method inserts a new file line into the priority queue using the 
     * comparator to place it in a proper position where the order is descending.
     * 
     * @param fl The new file line.
     */
    public void insert(FileLine fl) throws PriorityQueueFullException {
    	//If the array is full throw a PriorityQueueFullException.
    	if(curMinPos == (maxSize - 1)) {
    		throw new PriorityQueueFullException();
    	}
    	
    	//If the array is empty add the new line to the first position in the array.
		if(isEmpty()) {
			lines[0] = fl;
			//Keep track of the position of the minimum value.
			curMinPos = 0;
		} else {
			for(int i = 0; i < lines.length; ++i) {
				//If there aren't any values smaller than the new value, place the new
				//line at the end of the queue and make it the new minimum value.
				if(lines[i] == null) {
					lines[i] = fl;
					++curMinPos;
					break;
				} else if(cmp.compare(fl, lines[i]) == 1){
					//If the new value is greater than one of the values in the queue
					//shift all the smaller values over and place the new value in the 
					//queue.
					for(int j = curMinPos; j >= i; --j) {
						lines[j + 1] = lines[j];
					}
					lines[i] = fl;
					++curMinPos;
					break;
				}
			}
		}
    }

	/**
	 * This method determines whether or not the priority queue is empty.
	 * 
	 * @return A boolean based on if the queue is empty.
	 */
    public boolean isEmpty() {
    	//If the curMinPos is equal to negative one there currently aren't 
    	//any items in the array. 
		if(curMinPos == -1) {
			return true;
		}
		return false;
    }
}