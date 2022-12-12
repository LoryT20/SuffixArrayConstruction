package suffixArrayConstruction;

import java.util.ArrayList;

public class MergeSort {
	public static ArrayList<int[]> mergeSort(ArrayList<int[]> whole, String str) {
	    ArrayList<int[]> L = new ArrayList<int[]>();
	    ArrayList<int[]> R = new ArrayList<int[]>();
	    int middle;
	    if (whole.size() == 1) {    
	        return whole;
	    } else {
	        middle = whole.size()/2;
	        for (int i=0; i<middle; i++) {
	                L.add(whole.get(i));
	        }
	        for (int i=middle; i<whole.size(); i++) {
	                R.add(whole.get(i));
	        }
	        L = mergeSort(L, str);
	        R = mergeSort(R, str);
	        merge(L, R, whole, str);
	    }
	    ArrayList<int[]> newList = new ArrayList<int[]>();
        for (int[] element : whole) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
	}
	
	private static void merge(ArrayList<int[]> L, ArrayList<int[]> R, ArrayList<int[]> whole, String str) {
	    int LIndex = 0;
	    int RIndex = 0;
	    int arrIndex = 0;
	    while (LIndex < L.size() && RIndex < R.size()) {
	    	char firstL = ' ';
	    	char firstR = ' ';
	    	String subL = str.substring(L.get(LIndex)[0],L.get(LIndex)[1]);
	    	String subR = str.substring(R.get(RIndex)[0],R.get(RIndex)[1]);
	    	if(subL.length()>0) {
	    		firstL = subL.charAt(0);
	    	}
	    	if(subR.length()>0) {
	    		firstR = subR.charAt(0);
	    	}
	    	//System.out.println(firstL+", "+firstR);
	    	//System.out.println("MSL:"+str.substring(L.get(LIndex)[0],L.get(LIndex)[1]));
	    	//System.out.println("MSR:"+str.substring(R.get(LIndex)[0],R.get(LIndex)[1]));
        	//System.out.println("Chu-Ching"+str.substring(L.get(LIndex)[0],L.get(LIndex)[1]).compareTo(str.substring(L.get(LIndex)[0],L.get(LIndex)[1])));
	        //if ( (str.substring(L.get(LIndex)[0],L.get(LIndex)[1]).compareTo(str.substring(R.get(LIndex)[0],R.get(RIndex)[1])) < 0)) {
	        if(firstL<firstR) {
	    		whole.set(arrIndex, L.get(LIndex));
	            LIndex++;
	        } else {
	        	whole.set(arrIndex, R.get(RIndex));
	            RIndex++;
	        }
	        arrIndex++;
	    }
	    ArrayList<int[]> rem;
	    int remIndex;
	    if (LIndex >= L.size()) {
	        rem = R;
	        remIndex = RIndex;
	    } else {
	        rem = L;
	        remIndex = LIndex;
	    }
	    for (int i=remIndex; i<rem.size(); i++) {
	    	whole.set(arrIndex, rem.get(i));
	        arrIndex++;
	    }
	}	
}
