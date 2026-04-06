package apcsa.githubtrack;

// Implement your CString class here

public class CString {

    public char[] str;

    public CString(String str) {
        this.str = str.toCharArray();
    }

    public CString(char[] str) {
        this.str = str;
    }

    public CString(int[] str) {
        this.str = new char[str.length];
        for(int i = 0; i < str.length; i++) {
            this.str[i] = (char) str[i];
        }
    }

    public void reverse() {
        for(int i = 0; i < str.length/2; i++) {
            char end = str[str.length - i - 1];
            str[str.length - i - 1] = str[i];
            str[i] = end;
        }
    }

    public void swap(int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public void selectionSort() {
        for(int i = 0; i < str.length - 1; i++) {
            int min = i;
            for(int j = i; j < str.length; j++) {
                if(str[j] < str[min]) {
                    min = j;
                }
            }
            swap(min, i);
        }
    }

    public void insertionSort() {
        for(int i = 1; i < str.length; i++) {
            for(int j = i; j > 0 && str[j] < str[j-1]; j--) {
                swap(j, j-1);
            }
        }
    }

    public void sortAscending() {
        selectionSort();
    }
    
    public void sortDescending() {
        insertionSort();
        reverse();
    }

    @Override
    public String toString() {
        return new String(str);
    }
}
