import java.io.*;
import java.util.*;
import java.lang.*;

/**
* Semester:         CS367 Spring 2017
* PROJECT:          p3
* FILE:             Reducer.java
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
 * Reducer solves the following problem: given a set of sorted input files (each
 * containing the same type of data), merge them into one sorted file. 
 *
 */
public class Reducer {
    // list of files for stocking the PQ
    private List<FileIterator> fileList;
    private String type,dirName,outFile;
    private boolean rEmpty = true;
    private String key;
	private String[] words;
	private boolean eJoinR = false;

    public static void main(String[] args) throws PriorityQueueFullException, PriorityQueueEmptyException, IOException {
		if (args.length != 3) {
			System.out.println("Usage: java Reducer <weather|thesaurus> <dir_name> <output_file>");
			System.exit(1);
		}

		String type = args[0];
		String dirName = args[1];
		String outFile = args[2];

		Reducer r = new Reducer(type, dirName, outFile);
		r.run();
	
    }

	/**
	 * Constructs a new instance of Reducer with the given type (a string indicating which type of data is being merged),
	 * the directory which contains the files to be merged, and the name of the output file.
	 */
    public Reducer(String type, String dirName, String outFile) {
		this.type = type;
		this.dirName = dirName;
		this.outFile = outFile;
    }

	/**
	 * Carries out the file merging algorithm described in the assignment description. 
	 * @throws PriorityQueueFullException 
	 * @throws PriorityQueueEmptyException 
	 * @throws IOException 
	 */
    public void run() throws PriorityQueueFullException, PriorityQueueEmptyException, IOException {
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		Arrays.sort(files);

		Record r = null;

		// list of files for stocking the PQ
		fileList = new ArrayList<FileIterator>();

		for(int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isFile() && f.getName().endsWith(".txt")) {
				fileList.add(new FileIterator(f.getAbsolutePath(), i));
			}
		}

		switch (type) {
		case "weather":
			r = new WeatherRecord(fileList.size());
			break;
		case "thesaurus":
			r = new ThesaurusRecord(fileList.size());
			break;
		default:
			System.out.println("Invalid type of data! " + type);
			System.exit(1);
		}
		Comparator<FileLine> cmp = r.getComparator();
		MinPriorityQueueADT<FileLine> queue = new FileLinePriorityQueue(fileList.size() * 2, cmp);
		PrintWriter output = new PrintWriter(new FileWriter(outFile));
		
		for(int i = 0; i < fileList.size(); i++){
    		if(fileList.get(i).hasNext())
					queue.insert(fileList.get(i).next());
    	}
    	while(!queue.isEmpty()){
			FileLine li = queue.removeMin();
			if(rEmpty == true){
					if(type.equals("weather")){
						words = li.getString().split(",");
					}
					if(type.equals("thesaurus")){
						words = li.getString().split(":");	
					}
					r.join(li);
					if(li.getFileIterator().hasNext())
						queue.insert(li.getFileIterator().next());
					rEmpty = false;
			}else{
					String[] keyword = new String[2];
					if(type.equals("thesaurus"))
						keyword[0] = li.getString().split(":")[0];
					if(type.equals("weather")){
						keyword[0] = li.getString().split(",")[0];
						keyword[1] = li.getString().split(",")[1];
					}
					
					if(keyword[0].equals(words[0]))
						System.out.println(keyword[1] + " " + words[1]);
						if(type.equals("thesaurus"))
							eJoinR = true;
						else if(keyword[1].equals(words[1]))
							eJoinR = true;
					
					if(eJoinR == true){
						System.out.println("got here");
						r.join(li);
						if(li.getFileIterator().hasNext())
							queue.insert(li.getFileIterator().next());
						eJoinR = false;
					}
					else{
						System.out.println(r.toString());
						output.println(r.toString());
						r.clear();
						rEmpty = true;
					}
			}
    	}
    	output.close();
    }
}
