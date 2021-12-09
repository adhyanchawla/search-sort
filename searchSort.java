import java.util.*;

public class searchSort {

    // https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1#
    public class Student implements Comparable<Student>{
        int p, c, m;
        
        Student() {
            
        }
        
        public Student(int p, int c, int m) {
            this.p = p;
            this.c = c;
            this.m = m;
        }
        
        public int compareTo(Student o) {
            if(this.p != o.p) {
                return this.p - o.p;
            } else if(this.c != o.c){
                return o.c - this.c;
            } else return this.m - o.m;
        }
    }
    
    public void customSort (int phy[], int chem[], int math[], int N)
    {
        Student[] arr = new Student[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = new Student(phy[i], chem[i], math[i]);
        }
        
        Arrays.sort(arr);
        
        for(int i = 0; i < N; i++) {
            phy[i] = arr[i].p;
            chem[i] = arr[i].c;
            math[i] = arr[i].m;
        }
    }


    // lc 658
    public class Pair implements Comparable<Pair> {
        int val;
        int gap;
        
        Pair(int val, int gap) {
            this.val = val;
            this.gap = gap;
        }
        
        public int compareTo(Pair o) {
            if(this.gap == o.gap) {
                return this.val - o.gap;
            } else return this.gap - o.gap;
        }
    }
    
    //O(nlogk)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair>pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            if(pq.size() < k) {
                pq.add(new Pair(arr[i], (int)Math.abs(arr[i] - x)));
            } else {
                if(pq.peek().gap > (int)Math.abs(arr[i] - x)) {
                    pq.remove();
                    pq.add(new Pair(arr[i], (int)Math.abs(arr[i] - x)));
                }
            }
        }
        
        while(pq.size() != 0) {
            ans.add(pq.peek().val);
            pq.remove();
        }
        
        Collections.sort(ans);
        return ans;
    }

    //lc 658
    //O(logn + klogk)
    //using binary search
    public List<Integer> findClosestElements_opti(int[] arr, int k, int x) {
        int n = arr.length;
        int si = 0;
        int ei = n - 1;
        List<Integer> ans = new ArrayList<>();
        if(k == 1) {
            if(n <= 3) {
                if(n == 1) {
                    ans.add(arr[0]);
                    return ans;
                } else if(n == 2) {
                    if((int)Math.abs(arr[0] - x) <= (int)Math.abs(arr[1] - x)) {
                        ans.add(arr[0]);
                        return ans;
                    } else {
                        ans.add(arr[1]);
                        return ans;
                    }
                } else {
                    int min = (int)Math.abs(arr[0] - x);
                    int ele = arr[0];
                    for(int i = 1; i < 3; i++) {
                        if(min > (int)Math.abs(arr[i] - x)) {
                            min = (int)Math.abs(arr[i] - x);
                            ele = arr[i];
                        }
                    }
                    //System.out.println(ele);
                    ans.add(ele);
                    return ans;
                }
            }
        }
        
        int mid = 0;
        while(si <= ei) {
            mid = si + (ei - si) / 2;
            if(arr[mid] == x) {
                break;
            }
            if(arr[mid] > x) {
                ei = mid - 1;
            } else si = mid + 1;
        }
        
        int e1 = mid - 1;
        int e2 = mid;
        
        
        while((e1 >= 0 || e2 < n) && k > 0) {
            int diff1 = e1 >= 0 ? (int)Math.abs(x - arr[e1]) : (int)1e9;
            int diff2 = e2 < n ? (int)Math.abs(x - arr[e2]) : (int)1e9;
            if(e1 >= 0 && diff1 <= diff2) {
                ans.add(arr[e1]);
                e1--;
                k--;
            } else {
                if(e1 < 0) {
                    while(e2 < n && k-- > 0) {
                        ans.add(arr[e2++]);
                    }
                } else if(e2 >= n) {
                    while(e1 >= 0 && k-- < 0) {
                        ans.add(arr[e1--]);
                    }
                } else {
                    ans.add(arr[e2]);
                    e2++;   
                    k--;
                    }
                }
            }
        
        Collections.sort(ans);
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1#
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        int s1 = 0;
        int s2 = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        
        while(s1 < n && s2 < m) {
            if(arr1[s1] < arr2[s2] && ((ans.size() > 0 && ans.get(ans.size() - 1) != arr1[s1]) || ans.size() == 0)) {
                ans.add(arr1[s1]);
                s1++;
            } else if(arr1[s1] > arr2[s2] && ((ans.size() > 0 && ans.get(ans.size() - 1) != arr2[s2]) || ans.size() == 0)) {
                ans.add(arr2[s2]);
                s2++;
            } else if(arr1[s1] == arr2[s2] && (ans.size() > 0 && ans.get(ans.size() - 1) != arr1[s1]) && (ans.size() > 0 && ans.get(ans.size() - 1) != arr2[s2]) || ans.size() == 0){
                ans.add(arr1[s1]);
                s1++;
                s2++;
            } else if(arr1[s1] < arr2[s2]) {
                s1++;
            } else s2++;
        }
        
        while(s1 < n) {
            if((ans.size() > 0 && ans.get(ans.size() - 1) != arr1[s1]) || ans.size() == 0)
            ans.add(arr1[s1]);
            
            s1++;
        }
        
        while(s2 < m) {
            if((ans.size() > 0 && ans.get(ans.size() - 1) != arr2[s2]) || ans.size() == 0)
            ans.add(arr2[s2]);
            
            s2++;
        }
        
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    public boolean findPair(int arr[], int n, int target)
    {
        int i = 0;
        int j = 1;
        
        Arrays.sort(arr);
        while(i < n && j < n) {
            int diff = arr[j] - arr[i];
            if(target == diff) {
                return true;
            } else if(target > diff) {
                j++;
            } else {
                i++;
            }
        }
        return false;
    }

    // https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1/?category
    static int maxStep(int arr[], int N)
    {
       int md = 0;
       int count = 0;
       int maxCount = 0;
       int cd = 0;
       
       int i = 0;
       int j = 1;
       
       //Arrays.sort(arr);
       while(i < N && j < N) {
           cd = arr[j] - arr[i];
           if(cd > 0 && md < cd) {
               md = cd;
               count++;
               if(maxCount < count) {
                   maxCount = count;
               }
               j++;
           } else {
               count = 0;
               md = 0;
               cd = 0;
               i = j;
               j = j + 1;
               //j++;
           }
       }
       return maxCount;
    }

    // https://www.geeksforgeeks.org/counting-inversions/
    static long count = 0;
    
    static long inversionCount(int arr[], int N)
    {
        count = 0;
        mergeSort(arr, 0, N - 1);
        return count;
    }
    
    static int[] merge(int[] left, int[] right) {
        int n1 = left.length;
        int n2 = right.length;
        int s1 = 0;
        int s2 = 0;
        int[] ans = new int[left.length + right.length];
        int c = 0;
        while(s1 < n1 && s2 < n2) {
            if(left[(int)s1] < right[s2]) {
                ans[c++] = left[s1++];
            } else {
                count += n1 - s1;
                ans[c++] = right[s2++];
            }
        }
        
        while(s1 < n1) {
            ans[c++] = left[s1++];
        }
        
        while(s2 < n2) {
            ans[c++] = right[s2++];
        }
        
        return ans;
    }
    
    static int[] mergeSort(int arr[], int lo, int hi) {
        if(lo == hi) {
            return new int[]{arr[lo]};
        }
        
        int mid = lo + (hi - lo) / 2;
        int[] left = mergeSort(arr, lo, mid);
        int[] right = mergeSort(arr, mid + 1, hi);
        return merge(left, right);
    }

    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1#
    int max_sum(int A[], int n)
    {
        int sum = 0;
        int S0 = 0;
        for(int i = 0; i < n; i++) {
            sum += A[i];
            S0 += A[i] * i;
        }
        
        int maxSum = 0;
        int prevSum = S0;
        int currSum = 0;
        for(int i = 0; i < n; i++) {
            currSum = prevSum + sum - n * A[n - i - 1];
            maxSum = Math.max(maxSum, currSum);
            prevSum = currSum;
        }
	   
	   return maxSum;
    }	

    //lc 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 0) {
            return (double)0;
        }
        else if(nums1.length == 0 || nums2.length == 0) {
            return (nums1.length == 0) ? (nums2.length % 2 == 0 ? (double)(nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2 : nums2[nums2.length / 2]) : (nums1.length % 2 == 0 ? (double)(nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2 : nums1[nums1.length / 2]);
        }
        
        int s1 = 0;
        int s2 = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] ans = new int[n1 + n2];
        int c = 0;
        while(s1 < n1 && s2 < n2) {
            if(nums1[s1] < nums2[s2]) {
                ans[c++] = nums1[s1++];
            } else {
                ans[c++] = nums2[s2++];
            }
        }
        
        while(s1 < n1) {
            ans[c++] = nums1[s1++];
        }
        
        while(s2 < n2) {
            ans[c++] = nums2[s2++];
        }
        
        
        double mid = 0;
        if(ans.length % 2 == 1) {
            mid = (double)ans[ans.length / 2];
            return mid;
        } else {
            mid = (double)(ans[ans.length / 2] + ans[ans.length / 2 - 1])/2;
            return mid;
        }
        
    }

    //lc 4 optimised
    // O(log(n + m))
    public double findMedianSortedArrays_opti(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 0) {
            return (double)0;
        }
        else if(nums1.length == 0 || nums2.length == 0) {
            return (nums1.length == 0) ? (nums2.length % 2 == 0 ? (double)(nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2 : nums2[nums2.length / 2]) : (nums1.length % 2 == 0 ? (double)(nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2 : nums1[nums1.length / 2]);
        }
        
        if(nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int n1 = nums1.length;
        int n2 = nums2.length;
        int lo = 0;
        int hi = n1;
        int l = n1 + n2;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int bl = (l + 1)/ 2 - mid;
            
            int mm1 = (mid == 0) ? -(int)1e7 : nums1[mid - 1];
            int m = (mid == n1) ? (int)1e7 : nums1[mid];
            int bm1 = (bl == 0) ? -(int)1e7 : nums2[bl - 1];
            int b = (bl == n2) ? (int)1e7 : nums2[bl];
            
            if(mm1 <= b && bm1 <= m) {
                int lmax = Math.max(mm1, bm1);
                int rmin = Math.min(m, b);
                if(l % 2 != 0) {
                    return (double)lmax;
                } else if(l % 2 == 0){
                    return (double)(lmax + rmin) / 2;
                }
            } else if(bm1 > m) {
                lo = mid + 1;
            } else if(b < mm1) {
                hi = mid - 1;
            }
        }
        return (double)0;
    }

    // https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1
    public long findMinDiff (ArrayList<Long> A, long n, long m)
    {
        Collections.sort(A);
        int lo = 0;
        int hi = (int)(m - 1);
        
        long min = (long)(1e9 + 7);  
        while(hi < n) {
            min = Math.min(min, A.get(hi) - A.get(lo));
            lo++;
            hi++;
        }
        
        return min;
    }

    //lc 410 : refer to allocate min no of pages
    public boolean isPossible(int[] nums, int mid, int m) {
        int sa = 1;
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            
            if(sum > mid) {
                sa++;
                sum = nums[i];
            }
        }
        return sa <= m;
    }
    
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for(int ele : nums) {
            sum += ele;
            max = Math.max(max, ele);
        }
        int lo = max;
        int hi = sum;
        
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(isPossible(nums, mid, m)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return ans;
    }


    //lc 1011
    public boolean isPossible1(int[] weights, int mid, int days) {
        int sa = 1;
        int sum = 0;
        int n = weights.length;
        for(int i = 0; i < n; i++) {
            sum += weights[i];
            
            if(sum > mid) {
                sa++;
                sum = weights[i];
            }
        }
        
        return sa <= days;
    }
    
    public int shipWithinDays(int[] weights, int days) {
        int max = 0;
        int sum = 0;
        for(int ele : weights) {
            sum += ele;
            max = Math.max(ele, max);
        }
        
        int lo = max;
        int hi = sum;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(isPossible1(weights, mid, days)) {
                ans = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1#
    int countTriplet(int arr[], int n) {
        Arrays.sort(arr);
        int count = 0;
        
        int idx = arr.length - 1;
        while(idx >= 0) {
            int lo = 0;
            int hi = idx - 1;
            while(hi >= 0 && lo < idx && lo < hi) {
                if(arr[lo] + arr[hi] == arr[idx]) {
                    count++;
                    lo++;
                    hi--;
                } else if(arr[lo] + arr[hi] < arr[idx])
                    lo++;
                else hi--;        
            }
            idx--;
        }
        
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/count-possible-triangles-1587115620/1
    static int findNumberOfTriangles(int arr[], int n)
    {
        Arrays.sort(arr);
        int idx = arr.length - 1;
        int count = 0;
        while(idx >= 0) {
            int hi = idx - 1;
            int lo = 0;
            while(hi >= 0 && lo < n && lo < hi) {
                if(arr[lo] + arr[hi] > arr[idx]) {
                    count += hi - lo;
                    hi--;
                } else lo++;
            }
            idx--;
        }
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/counting-elements-in-two-arrays/1#
    public static ArrayList<Integer> countEleLessThanOrEqual(int arr1[], int arr2[], int m, int n)
    {
       // add your code here
       ArrayList<Integer> ans = new ArrayList<>();
      // Arrays.sort(arr1);
       Arrays.sort(arr2);
       
       int idx = 0;
       
       while(idx < m) {
           int lo = 0;
           int hi = n - 1;
           while(lo <= hi) {
               int mid = lo + (hi - lo) / 2;
               if(arr2[mid] <= arr1[idx]) {
                   lo = mid + 1;
               } else hi = mid - 1;
           }
           ans.add(lo);
           idx++;
       }
       return ans;
    }

    // https://practice.geeksforgeeks.org/problems/counts-zeros-xor-pairs0349/1#
    public static long calculate (int arr[], int n) {
        //Complete the function
        long count = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        
        for(int ele : arr) {
            if(!map.containsKey(ele)) {
                map.put(ele, (long)1);
            } else {
                map.put(ele, map.get(ele) + 1);
            }
        }
        
        for(int ele : map.keySet()) {
            if(map.get(ele) > 1) {
                long num = map.get(ele);
                count += ((num) * (num - 1)) / 2;
            }
        }
        
        return count;
    }

}
