import java.util.*;

public class binarySearch {

    //binary search
    public static int BS(int[] arr, int ele) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] > ele) {
                end = mid - 1;
            } else start = mid + 1;

        }
        return -1;
    }

    public static int BS(int[] arr, int start, int end, int ele) {
        int n = arr.length;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] == ele) return mid;
            else if(arr[mid] > ele) {
                end = mid - 1;
            } else start = mid + 1;

        }
        return -1;
    }

    //binary search in reverse sorted array
    public static int descendingBS(int []arr, int ele) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == ele) {
                return mid;
            } else if(arr[mid] > ele) {
                start = mid + 1;
            } else end = mid - 1;
        }

        return -1;
    }

    //order agnostic binary search: we dont know in which way the sorting is done: ascending/descending?
    public static int OABS(int[] arr, int ele) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        if(arr[start] <= arr[end]) {
            return BS(arr, ele);
        } else {
            return descendingBS(arr, ele);
        }
    }

    public int findFirst(int[] arr, int ele) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        
        int fe = -1;
        
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == ele) {
                if(((mid - 1 >= 0) && arr[mid] != arr[mid - 1]) || (mid == 0 && arr[mid] == ele)) {
                    fe = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            } else if(arr[mid] > ele) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return fe;
    }

    public int findLast(int[] arr, int ele) {
        int n = arr.length;
        int le = -1;
        int start = 0;
        int end = n - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == ele) {
                if((mid + 1 < n && arr[mid] != arr[mid + 1]) || (mid == n - 1 && arr[mid] == ele)) {
                    le = mid;
                    break;
                } else {
                    start = mid + 1;
                }
            } else if(arr[mid] > ele) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return le;
    }

    //find first and last occurance
    public int[] searchRange(int[] arr, int ele) {
        
        int n = arr.length;
        int fe = findFirst(arr, ele);
        int le = findLast(arr, ele);

        return new int[] {fe, le};
    }

    //count of elements in sorted array
    int count(int[] arr, int n, int x) {
        int fe = findFirst(arr, x);
        int le = findLast(arr, x);
        
        return (fe != -1 && le != -1) ? le - fe + 1 : 0;
    }

    //no of times a sorted array is rotated
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int n = nums.length;        
        
        int st = 0;
        int en = n - 1;
        
        if(nums[st] < nums[st + (en - st)/2] && nums[st + (en - st)/2] < nums[en]) {
            return nums[st];
        }
        
        while(st <= en) {
            int mid = st + (en - st) / 2;
            
            if((mid + 1 < n && nums[mid + 1] > nums[mid]) && ((mid + n - 1 % n >= 0) && nums[(mid + n - 1) % n] > nums[mid]))
                return nums[mid];
            
            if(nums[st] <= nums[mid] && nums[mid] <= nums[en])
                return nums[st];
            
            if(nums[en] > nums[mid])
                en = mid - 1;
            else st = mid + 1;
        }
        
        return -1;
    }

    // search in nearly sorted array
    public static int searchInNearlySorted(int[] arr, int ele) {
        int n = arr.length;

        int si = 0;
        int ei = n - 1;

        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if((mid + n - 1) % n >= si & arr[(mid + n - 1) % n] == ele){
                return (mid + n - 1) % n;
            } else if(arr[mid] == ele) {
                return mid;
            } else if(mid + 1 <= ei && arr[mid + 1] == ele) return mid + 1;

            if(arr[mid] <= ele) {
                si = mid + 2;
            } else if(arr[mid] >= ele) {
                ei = mid - 2;
            }
        }

        return -1;
    }


    //floor in sorted array
    static int findFloor(int arr[], int n, long x)
    {
        int si = 0;
        int ei = n - 1;
        int floor = -1;
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(arr[mid] <= x) {
                if(arr[mid] == x) {
                    return mid;
                } else if(arr[mid] < x && floor <= arr[mid]) {
                    floor = mid;
                }
            }
            
            if(arr[mid] > x) {
                ei = mid - 1;
            } else si = mid + 1;
        }
        return (int)floor;
    }

    //ceil in sorted array
    static int findCeil(int arr[], int n, long x)
    {
        int si = 0;
        int ei = n - 1;
        int ceil = (int)1e9;
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(arr[mid] >= x) {
                if(arr[mid] == x) {
                    return mid;
                } else if(arr[mid] > x && ceil >= arr[mid]) {
                    ceil = mid;
                }
            }
            
            if(arr[mid] > x) {
                ei = mid - 1;
            } else si = mid + 1;
        }
        return (int)ceil;
    }

    //find pos of element in infinite array
    public static int searchInInfiniteArray(int[] arr, int ele) {
        int si = 0;
        int ei = 1;

        while(arr[ei] < ele) {
            si = ei;
            ei = ei * 2;
        }

        int ans = BS(arr, si, ei, ele);
        return ans;
    }

    public static int findPosOfFirstOne(int[] arr) {
        int n = arr.length;
        int st = 0;
        int en = 1;

        while(en < n && arr[en] != 1) {
            st = en;
            en = en * 2;
        }

        //System.out.println(st);

        if(st == 0)
        return 0;

        while(st <= en) {
            int mid = st + (en - st) / 2;
            if(mid < n && arr[mid] == 1) {
                if(arr[mid - 1] == 0) {
                    return mid;
                } else en = mid - 1;
            } 
            if(mid < n && arr[mid] == 0) {
                st = mid + 1;
            } else en = mid - 1;
        }

        return -1;
    }

    public static int minAbsoluteDifferenceElementInSortedArray(int[] arr, int ele) {
        int n = arr.length;
        int ans1 = BS(arr, ele);
        int min = -1;
        if(ans1 != -1) {
            return ele;
        } else {
            int ans2 = findFloor(arr, n, ele);
            System.out.println(ans2);
            if(ans2 != -1) {
                min = arr[ans2];
            }
            int ans3 = findCeil(arr, n, ele);
            System.out.println(ans3);
            if((min != -1 && ans3 != (int)1e9 && (int)Math.abs(min - ele) > (int)Math.abs(arr[ans3] - ele)) || ans3 != (int)1e9) {
                min = arr[ans3];
            }
        }
        return min;
    }

    //lc 162
    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0;
        int n = nums.length;
        int si = 0;
        int ei = n - 1;
        
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(((mid + n - 1) % n >= 0 && nums[(mid + n - 1) % n] < nums[mid]) && (mid + 1 < n && nums[mid + 1] < nums[mid]) || (mid + 1 < n && mid == 0 && nums[mid] >= nums[mid + 1]) || (mid - 1 >= 0 && mid == n - 1 && nums[mid] >= nums[mid - 1])) {
                return mid;
            }
            
            if((mid + n - 1) % n >= 0 && (mid + 1) < n && nums[(mid + n - 1) % n] > nums[mid + 1])
                ei = mid - 1;
            else si = mid + 1;
        }
        
        return ei;
    }



    // https://practice.geeksforgeeks.org/problems/maximum-value-in-a-bitonic-array3001/1
    int findMaximum(int[] arr, int n) {
        if(arr.length == 1) return arr[0];
        
        //int n = arr.length;
        int si = 0;
        int ei = n - 1;
        if(arr[si + (ei - si) / 2] < arr[ei]) {
            return arr[ei];
        }
        
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(mid > 0 && mid < n - 1) {
                if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    return arr[mid];
                } else if(arr[mid + 1] > arr[mid]) {
                    si = mid + 1;
                } else if(arr[mid - 1] > arr[mid]) {
                    ei = mid - 1;
                }
            } else if(mid == n - 1) {
                if(arr[mid] > arr[n - 2]) {
                    return arr[mid];
                } else return arr[n - 2];
            } else if(mid == 0) {
                if(arr[mid] > arr[mid + 1]) {
                    return arr[mid];
                } else return arr[mid + 1];
            }
        }
        
        return -1;
    }

    //allocate min no of pages
    public static boolean isPossible(int[] A, int mid, int M) {
        int st = 1;
        int sum = 0;
        
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            
            if(sum > mid) {
                st++;
                sum = A[i];
            }
        }
        return st <= M;
    }
    
    
    
    public static int findPages(int[]A,int N,int M)
    {
       int ans = 0;
       int sum = 0;
       int max = 0;
       for(int ele : A) {
           max = Math.max(ele, max);
           sum += ele;
       }
       
       int lo = max;
       int hi = sum;
       
       while(lo <= hi) {
           int mid = lo + (hi - lo) / 2;
           if(isPossible(A, mid, M)) {
               ans = mid;
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
       }
       return ans;
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 9, 10, 12, 15};
        int[] darr = {20, 17, 15, 14, 13, 12, 10, 9, 8, 4};
        int narr[] =  {10, 3, 40, 20, 50, 80, 70};
        int[] barr = {0, 0, 0, 0, 0, 0};
        //System.out.println(BS(arr, 4));
        //System.out.println(descendingBS(darr, 4));
        //System.out.println(OABS(darr, 9));
        //System.out.println(searchInNearlySorted(narr, 70));
        //System.out.println(findPosOfFirstOne(barr));
        System.out.println(minAbsoluteDifferenceElementInSortedArray(arr, 0));
    }
}