package apcsa.githubtrack;
import java.util.ArrayList;
import java.util.stream.*;

// Implement your CStringUtil class here

public final class CStringUtil {

    private CStringUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static boolean isPalindrome(CString c) {
        ArrayList<Character> str = new ArrayList<>(); // ArrayList that will contain chars without spaces.
        for(int i = 0; i < c.str.length; i++) {
            if(c.str[i] != ' ') { // only add to arrayList if the character is not a space
                str.add(Character.toLowerCase(c.str[i])); // toLowerCase to ignore case
            }
        }
        for(int i = 0; i < str.size()/2; i++) { // loop to half of array
            // if the element at the mirror opposite of the array doesn't match, return false
            if(str.get(i) != str.get(str.size() - i - 1)) { 
                return false;
            }
        }
        return true; // if it passes the loop, return true
    }

    public static int[] toNumerical(CString c, int offset) {
        // intstream to get an ascending list of integers. Map the integers to elements at that index, plus the passed-in offset
        return IntStream.range(0, c.str.length).map(i -> c.str[i] + offset).toArray(); 
    }

    public static int maxMirror(int[] c) {
        if(c.length == 1) return 1; // if the array is length 1, it will have a maxMirror of 1 no matter what
        int current = 0; // current mirror size
        int max = 0; // largest mirror size
        for(int right = c.length - 1; right >= 0; right--) { // right index variable; starts at end of array, goes to beginning
            for(int left = 0; left < right; left++) { // left index variable; starts at beginning of array, goes to end
                // after a check to see if left and right are in the bounds of the array, check to see if the elements at those indices are equal
                while(left < c.length && right >= 0 && c[left] == c[right]) {
                    // if so, a mirror section is found, so increment left, decrement right, and increment current
                    left++;
                    right--;
                    current++;
                }
                // when mirror section ends, update max and set current to 0
                max = Math.max(max, current);
                current = 0;
            }
        }
        // return maximum mirror section length
        return max;
    }

    public static int maxMirror(CString c) { // see int[] version
        if(c.str.length == 1) return 1;
        int current = 0;
        int max = 0;
        for(int right = c.str.length - 1; right >= 0; right--) {
            for(int left = 0; left < right; left++) {
                while(left < c.str.length && right >= 0 && c.str[left] == c.str[right]) {
                    left++;
                    right--;
                    current++;
                }
                max = Math.max(max, current);
                current = 0;
            }
        }
        return max;
    }

    /*

    Previous memeifyArray code:
    
    private static class SevenLogEntry {
        private int index;
        private boolean add;

        private SevenLogEntry(int index, boolean add) {
            this.index = index;
            this.add = add;
        }

        private int getIndex() {
            return index;
        }

        private boolean getAdd() {
            return add;
        }

        @Override
        public String toString() {
            return add ? "+" : "-" + index;
        }
    }

    private static class SevenLog { // helper class for memifyArray
        private ArrayList<SevenLogEntry> log; // log to keep track of when 7s were added and held back in the memifyArray method

        private SevenLog() {
            log = new ArrayList<>();
        }

        private void add(int index, boolean add) { // add an entry with index to 7 list. boolean add represents whether the seven was added to the array or whether it was held back. Sevens that are added have a positive index, and sevens that are held back have a negative index. Since with regular 0-start indexing an index of 0 would be ambiguous, indexes start at 1. These index quirks are encapsulated in this class, so it is not apparent to the user.
            if(log.size() > 0 && log.getLast().getAdd() != add) { // if log has any entries and the last entry is the opposite of the entry to be added
                log.removeLast(); // remove instead of add because the opposite entries cancel out.
            } else {
                log.add(new SevenLogEntry(index, add)); // add an entry. If it is adding a 7 to arr in the memifyArray method, positive index, and if it is holding back a 7, negative index. Indexes are incremented (decremented if negative) by 1 so an index of 0 is not ambiguous whether it is add or hold back.
            }
        }

        private void close(ArrayList<Integer> arr) {
            for(int i = 0; i < log.size(); i++) {
                if(log.get(i).getAdd()) { // if an add to the array wasn't canceled with a remove, remove the 7
                    arr.remove(log.get(i).getIndex());
                } else { // if a remove to the array wasn't canceled with an add, re-add the 7 in the index it was removed from
                    arr.add(log.get(i).getIndex());
                }
            }
        }

        private SevenLogEntry get(int i) { // get regular index at i
            return log.get(i);
        }

        private SevenLogEntry getLast() {
            return log.getLast();
        }

        @Override
        public String toString() {
            return log.toString();
        }
        
    }

    public static int[] memeifyArray(int[] nums) {

        ArrayList<Integer> arr = new ArrayList<>(); // array to be returned
        SevenLog log = new SevenLog(); // indices of 7s added or held back.

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 6) {
                arr.add(nums[i]); // add the 6 to arr
                arr.add(7); // place a 7 at incremented index
                log.add(arr.size()-1, true); // add to log that a 7 was placed at the last index of arr
            } else if(nums[i] == 7) {
                log.add(i, false); // add to log that a 7 was not placed when it was encountered
            } else {
                arr.add(nums[i]); // if it is another number, add it to the array
            }
        }

        log.close(arr); // any leftover logs will be finalized in the array

        int[] a = new int[arr.size()]; // convert arrayList to int[]
        for(int i = 0; i < a.length; i++) {
            a[i] = arr.get(i);
        }
        return a;
    }
    */

    public static void swap(int[] arr, int i, int j) { // simple swap method to help memifyArray
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] memeifyArray(int[] nums) {

        for(int i = 0; i < nums.length; i++) { // loop through the array;
            if(nums[i] == 6) { 
                int j = 0; // index variable for while loop
                while(nums[j] != 7) { // if a 6 is encountered, loop through the array until you find a 7
                    if(nums[j] == 6) { // if a 6 is found, skip an index, so the 7 afterwards is not disturbed
                        j++;
                    }
                    j++; // increment variable as normal
                    if(j >= nums.length) return nums; // if the loop goes out of bounds before finding a stray 7, there are no more 7s so return the array as it is
                }
                swap(nums, i + 1, j); // swap the index after the 6 with the found 7 index
            }
        }
        return nums; // return array



        /* 
        
        Previous code
        
        int sevensToSkip = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 6) {
                int sevenIndex = 0;
                int sevensSkipped = 0;
                while(true) {
                    if(sevenIndex >= nums.length) return nums;
                    if(nums[sevenIndex] == 7) {
                        sevensSkipped++;
                    }
                    if(sevensSkipped >= sevensToSkip) {
                        break;
                    } else {
                        sevenIndex++;
                    }
                }
                swap(nums, i + 1, sevenIndex);
            }
        }
        return nums; */
    }

    public static boolean nestedSequence(CString out, CString inn) {
        out.insertionSort(); // sort arrays
        inn.insertionSort();
        int[] outer = toNumerical(out, 0); // convert to integer arrays
        int[] inner = toNumerical(inn, 0);
        int o = 0; // outer index
        int i = 0; // inner index
        while(o < outer.length && i < inner.length) { // run while both indices are in the bounds of their arrays
            if(outer[o] < inner[i]) { // if element in outer array at index o is smaller, increment o.
                o++;
            } else if (outer[o] == inner[i]) { // if outer equals inner, increment inner.
                i++;
            } else return false; // else if outer is larger than inner, outer does not contain one of inner's elements
        }
        return true; // if it passes the while loop, inner is contained in outer.
    }

    private static int countClumps(CString c) {
        int clumps = 0; // clump number
        char prev = '\u0000'; // variable to keep track of previous character
        for(int i = 0; i < c.str.length; i++) {
            if(prev != c.str[i]) { // if previous character is different from current, that means there is a new clump
                clumps++; // increment clumps
                prev = c.str[i]; // and set prev to current character
            }
        }
        return clumps;
    }

    public static CString decrypt(CString c) {
        /* CString a = new CString(c.toString());
        a.reverse();
        return a; */
        CString str = new CString(toNumerical(c, countClumps(c)-c.str.length)); // offset passed-in CString by number of clumps-total length
        //System.out.println(c.str.length);
        //System.out.println(countClumps(c));
        str.reverse(); // reverse CString
        return str;
    }
}
