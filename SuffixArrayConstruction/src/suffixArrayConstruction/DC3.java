package suffixArrayConstruction;

public class DC3 {
    public static int[] createSuffixArray(String str) {
        if (str == null || str.length() <= 1) {
        	System.out.println("NOPE");
        	return null;
        }
        	
        int n = str.length();
        int i = 0;
        int[] encode = new int[n + 3];       
        int max = Integer.MIN_VALUE;
        for (char c : str.toCharArray()) {
        	encode[i++] = c;
        	if(c>max) {
        		max = c;
        	}
        }
        int[] SA = suffixArray(encode, n, max);
    	//System.out.println("MAX = "+max);
        return SA;
    }
    private static int[] suffixArray(int[] nums, int n, int K) {
        int n0 = (n + 2) / 3;
        int n1 = (n + 1) / 3;
        int n2 = n / 3;
        int n02 = n0 + n2;
        int[] S12 = new int[n02 + 3];
        int[] SA12 = new int[n02 + 3];
        for (int i = 0, j = 0; i < n + (n0 - n1); ++i) if (0 != i % 3) {
        	S12[j++] = i;
        }
        radixPass(nums, S12, SA12, 2, n02, K);        
        radixPass(nums, SA12, S12, 1, n02, K);        
        radixPass(nums, S12, SA12, 0, n02, K);                
        int name = 0;
        int c0 = -1; 
        int c1 = -1; 
        int c2 = -1;
        for (int i = 0; i < n02; ++i) {
            if (c0 != nums[SA12[i]] || c1 != nums[SA12[i] + 1] || c2 != nums[SA12[i] + 2]) {
                name++;
                c0 = nums[SA12[i]];
                c1 = nums[SA12[i] + 1];
                c2 = nums[SA12[i] + 2];
            }
            if (1 == SA12[i] % 3) {
            	S12[SA12[i] / 3] = name;
            }
            else {
            	S12[SA12[i] / 3 + n0] = name;
            }
        }
        if (name < n02) {
            SA12 = suffixArray(S12, n02, name);
            for(int i = 0; i < n02; i++) {
            	S12[SA12[i]] = i + 1;
            }
        } 
        else {
            for(int i = 0; i < n02; i++) {
            	SA12[S12[i] - 1] = i;
            }
        }
        int[] s0 = new int[n0];
        int[] SA0 = new int[n0];
        for (int i = 0, j = 0; i < n02; i++) {
        	if (SA12[i] < n0) {
        		s0[j++] = 3 * SA12[i];
        	}
        }
        radixPass(nums, s0, SA0, 0, n0, K);
        int[] SA = new int[n];
        for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
            int i = SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2;
            int j = SA0[p]; 
            if (SA12[t] < n0 ? leq(nums[i], S12[SA12[t] + n0], nums[j], S12[j / 3]) :                 
                leq(nums[i], nums[i + 1], S12[SA12[t] - n0 + 1], nums[j], nums[j + 1], S12[j / 3 + n0])) {
                SA[k] = i;  
                t++;
                if (t == n02)
                for (k++; p < n0; p++, k++) SA[k] = SA0[p];
            } else {
                SA[k] = j;  
                p++;
                if (p == n0)  
                for (k++; t < n02; t++, k++) SA[k] = SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2;
            }
        }
        return SA;
    }
    private static void radixPass(int[] nums, int[] input, int[] output, int offset, int n, int k) {
        int[] count = new int[k + 1];
        for (int i = 0; i < n; ++i) {
        	//System.out.println(i+" and "+k);
        	//System.out.println(nums[input[i] + offset]);
        	count[nums[input[i] + offset]]++;
        }
        for (int i = 0, sum = 0; i < count.length; ++i) {
            int t = count[i];
            count[i] = sum;
            sum += t;
        } 
        for (int i = 0; i < n; ++i) output[count[nums[input[i] + offset]]++] = input[i];
    }
    private static boolean leq(int a1, int a2, int b1, int b2) {
        return a1 < b1 || (a1 == b1 && a2 <= b2);
    }
    private static boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
        return a1 < b1 || (a1 == b1 && leq(a2,a3,b2,b3));
    }
}

