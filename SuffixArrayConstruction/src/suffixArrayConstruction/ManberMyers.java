package suffixArrayConstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A Manber-Myers implementation that runs in O(n log^2 n) time due to the use
 * of Timsort instead of radix-sort.
 * 
 * Although this is worse asymptotically, due to the use of hybrid sorting
 * algorithm, its performance has been found to be superior to radix-sort (about
 * 2-3 times faster on average).
 * 
 * Moreover, it uses far less space than radix sort.
 *
 */
public class ManberMyers {

	/**
	 * To keep track of the triplets formed during Manber-Myers.
	 * 
	 * Ideally, we should not use this to make it more space-efficient. If I ever
	 * get over my laziness, I will get rid of it to save about 3 bytes of space per
	 * input character.
	 *
	 */
	private static class Triplet implements Comparable<Triplet> {

		int firstRank;
		int secondRank;
		int index;

		@Override
		public int compareTo(Triplet that) {
			return (this.firstRank == that.firstRank) ? (this.secondRank - that.secondRank)
					: (this.firstRank - that.firstRank);
		}

		@Override
		public String toString() {
			return String.format("(%d, %d, %d)", firstRank, secondRank, index);
		}
	}

	public static int[] createSuffixArray(ArrayList<String> str) {
		int n = str.size();

		int[] RANK = new int[n]; // Stores current suffix ranks; basically base-1 indexed inverse suffix array
		Triplet[] triplets = new Triplet[n];
		for (int i = 0; i < n; i++)
			triplets[i] = new Triplet();
		
//		System.out.println(str);
		ArrayList<String> sigma = HelperFunctions.getSortedAlphabet(str);
//		System.out.println(sigma);

		for (int i = 0; i < n; i++)
			RANK[i] = 1 + Collections.binarySearch(sigma, str.get(i));

		int e = 1; // will grow in powers of 2
		int rank; // to keep track of suffix ranks based on the first e characters
		do {

			int i;
			for (i = 0; i + e < n; i++) {
				// (current rank = ISA[i], next rank = ISA[i + e], current index)
				triplets[i].firstRank = RANK[i];
				triplets[i].secondRank = RANK[i + e];
				triplets[i].index = i;
			}
			for (; i < n; i++) {
				// if i + e >= n, then next rank = -1 (simulates $)
				triplets[i].firstRank = RANK[i];
				triplets[i].secondRank = -1;
				triplets[i].index = i;
			}

			//System.out.println("Before...\t"+Arrays.toString(triplets));
			Arrays.sort(triplets);
			//System.out.println("After...\t"+Arrays.toString(triplets));

			rank = 1; // smallest suffix has rank 1
			RANK[triplets[0].index] = 1;
			for (i = 1; i < n; i++) {
				if (triplets[i].firstRank != triplets[i - 1].firstRank
						|| triplets[i].secondRank != triplets[i - 1].secondRank) // if current triplet ranks are
																					// different from previous triplet
																					// ranks, then increment rank
					rank++;
				RANK[triplets[i].index] = rank;
			}
			e *= 2;
		} while (rank < n); // if rank reaches n, all suffixes have achieved distinct rank
		int[] SA = new int[n];
		for (int i = 0; i < n; i++)
			SA[RANK[i] - 1] = i;
		return SA;
	}
}
