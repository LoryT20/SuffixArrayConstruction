package suffixArrayConstruction;

import java.util.ArrayList;

public class BruteForce {

	public static void createSuffixArray(String str) {
		//ArrayList<String> suffixes = new ArrayList<>();
		ArrayList<int[]> suffixesIndex = new ArrayList<>();
		int n = str.length()-1;
		for(int i = 0; i < str.length(); i++) {
			int[] indexes = {i, n};
			suffixesIndex.add(indexes);
//			System.out.println(i);
		}
//		for(int x = 1; x <= n; x++) {
//			for(int i = 0; i <= n-x; i++) {
//				int j = i + x -1;
//				String sub = "";
//				for(int k = i; k <= j; k++) {
//					sub += str.substring(k,k+1);
//				}
//				if(!(suffixes.contains(sub))) {
//					suffixes.add(sub);
//					//System.out.println(sub);
//				}
//				//System.out.println();
//				sub = "";
//			}
//		}
//		System.out.println("suffixes: "+suffixesIndex);

//		System.out.print("[");
//
//		for(int [] suffix : suffixesIndex) {
//			System.out.print(str.substring(suffix[0],suffix[1])+", ");
//		}
//		System.out.print("]\n");

		ArrayList<int[]> sorted = MergeSort.mergeSort(new ArrayList<>(suffixesIndex), str);
		
//		System.out.print("[");
//		for(int [] suffix : sorted) {
//			System.out.print(str.substring(suffix[0],suffix[1])+", ");
//		}
//		System.out.print("]\n");

		
		ArrayList<Integer> SA = new ArrayList<>();;
		for(int i = 0; i < suffixesIndex.size(); i++) {
			//System.out.println(suffixes.indexOf("a$"));
			SA.add(suffixesIndex.indexOf(sorted.get(i)));
		}
		if(n<1000) {
			System.out.println(SA+" BF");
		}
		//System.out.println(suffixes);
	}
}
