package suffixArrayConstruction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {
	private static final long MEGABYTE = 1024L * 1024L;

	private static void testManberMyers(String str) {
		ArrayList<String> stringAsVector = HelperFunctions.stringToArrayList(str);
		int[] sa = ManberMyers.createSuffixArray(stringAsVector);
		if(sa.length < 100) System.out.println(Arrays.toString(sa));
	}
	
//	private static void testBruteForce(String str) {
//		ArrayList<String> stringAsVector = HelperFunctions.stringToArrayList(str);
//		int[] sa = BruteForce.createSuffixArray(stringAsVector);
//		if(sa.length < 100) System.out.println(Arrays.toString(sa));
//	}

	private static void testCorrectness(String str) throws Exception {
		//DC3 TEST
		double start1 = System.nanoTime();
		Runtime runtime1 = Runtime.getRuntime();
	    System.out.println("*** DC3 test ***");
	    int[] saDC3 = DC3.createSuffixArray(str);
		double end1 = System.nanoTime();      
		double duration1 = (end1-start1)/1000000000;
		if(saDC3.length < 100) System.out.println(Arrays.toString(saDC3));
	    System.out.println("Elapsed Time in seconds: "+ duration1);  
	    long memory1 = runtime1.totalMemory() - runtime1.freeMemory();
	    System.out.println("Used memory in megabytes: " + bytesToMegabytes(memory1));
		
		//MANBER MYERS TEST
		double start2 = System.nanoTime();
		Runtime runtime2 = Runtime.getRuntime();
	    System.out.println("\n*** Manber Myers Test ***");
		testManberMyers(str);
		double end2 = System.nanoTime();      
		double duration2 = (end2-start2)/1000000000;  
		System.out.println("Elapsed Time in seconds: "+ duration2);  
	    long memory2 = runtime2.totalMemory() - runtime2.freeMemory();
	    System.out.println("Used memory in megabytes: " + bytesToMegabytes(memory2));	
	    
		//Brute Force
		double start3 = System.nanoTime();
		Runtime runtime3 = Runtime.getRuntime();
	    System.out.println("\n*** Brute Force ***");
	    //testBruteForce(str);
		BruteForce.createSuffixArray(str);
		double end3 = System.nanoTime();      
		double duration3 = (end3-start3)/1000000000;
	    System.out.println("Elapsed Time in seconds: "+ duration3);
	    long memory3 = runtime3.totalMemory() - runtime3.freeMemory();
	    System.out.println("Used memory in megabytes: " + bytesToMegabytes(memory3));	
		
	}
	
	public static String readFileAsString(String fileName) {
	    String text = "";
	    try {
	      text = new String(Files.readAllBytes(Paths.get(fileName)));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return text;
	  }

	public static void main(String args[]) throws Exception {
		
//		testBWT("banana$");
//		testBWT("mississippix$");
//		testBWT("mississippilesslyormississippily$");
		
		System.out.println("___ TEST 1 ___");
		String test1 = "banana$";
		testCorrectness(test1);

//		System.out.println("___ TEST 2 ___");
//		String test2 = "mississippix$";
//		testCorrectness(test2);
//		
//		System.out.println("___ TEST 3 ___");
//		String test3 = "mississippilesslyormississippily$";
//		testCorrectness(test3);
		
//		
		System.out.println("\n___ 1984 ___");
		String bookFile = "1984.txt";
		String book = readFileAsString(bookFile);
		testCorrectness(book);
//		
//		System.out.println("\n___ Drac ___");
//		String dracFile = "Dracula.txt";
//		String drac = readFileAsString(dracFile);
//		testCorrectness(drac);
//		
//		
//		System.out.println("\n___ Bible ___");
//		String bibleFile = "bible.txt";
//		String bible = readFileAsString(bibleFile);
//		testCorrectness(bible);
//		
	}
	

	public static long bytesToMegabytes(long bytes) {
		    return bytes / MEGABYTE;
	}

}
