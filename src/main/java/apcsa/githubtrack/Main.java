package apcsa.githubtrack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
// Implement your main application logic here


public class Main {

    public static int largestAsciiValue(CString[] arr) {
        int largest = 0; // variable to store largest ASCII value
        for(CString str : arr) { // nested loop to loop through each individual char
            for(char c : str.str) {
                if(c > largest) largest = c; // update largest if needed
            }
        }
        return largest;
    }

    public static void rotate(CString[] arr, int d) {
        CString[] arr2 = new CString[arr.length]; // create a new array
        for(int i = 0; i < arr.length; i++) { // loop through arr
            // set each element in arr2 to the element in arr offsetted by the specified amount. 
            // The modulo is to make the wraparound work
            arr2[i] = arr[(i+d)%arr.length]; 
        }
        // set the old array equal to the new one
        for(int i = 0; i < arr.length; i++) {
            arr[i] = arr2[i];
        }
    }
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<CString> text = new ArrayList<>(); // stores words

        // scan file
        File f = new File("src/main/resources/secretMessage.txt");
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            text.add(new CString(s.next()));
        }
        s.close();
        
        // convert arraylist to array
        CString[] words = text.toArray(new CString[0]);
        // decrypt each word
        for(int i = 0; i < words.length; i++) {
            words[i] = CStringUtil.decrypt(words[i]);
        }

        //rotate words in array
        rotate(words, largestAsciiValue(words)-60);
        //print them out
        System.out.println(Arrays.stream(words).map(Object::toString).collect(Collectors.joining(" ")));


    }
}