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
* Author2: Michael Buchman, mbuchman@wisc.edu, mbuchman, 001
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

    public static void main(String[] args) {
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
	 */
    public void run() {
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
		//TODO
		MinPriorityQueueADT<FileLine> queue = new FileLinePriorityQueue((int)files[0].length(), r.getComparator());

		
    }
}
