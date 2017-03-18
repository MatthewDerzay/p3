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
			lines[currPos + 1] = fl;
			++currPos;
		}
		else{
			
		}
    }

    public boolean isEmpty() {
		if(lines[0] == null)
			return true;
		return false;
    }
}
