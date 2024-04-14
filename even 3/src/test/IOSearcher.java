package test;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class IOSearcher {
    static LinkedHashSet<String> hash = new LinkedHashSet<>();
    public static boolean search(String word, String...fileName){
        for (int i = 0; i < fileName.length; i++)
            hash.add(fileName[i]);
        Iterator<String> it = hash.iterator();// Get the iterator
        while (it.hasNext()) {
            try{
                File myObj = new File(it.next());
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNext()) {
                    String data = myReader.findInLine(word);
                    if (data != null)
                        return true;
                    myReader.next();
                }
                myReader.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
