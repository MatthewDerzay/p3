import java.util.Comparator;

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
