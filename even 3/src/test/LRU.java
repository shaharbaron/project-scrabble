package test;
import java.util.LinkedHashSet;
import java.util.Iterator;


public class LRU implements CacheReplacementPolicy{
    LinkedHashSet<String> words = new LinkedHashSet<String>();
    int capacity;
    public LRU() {
        this.words = words;
    }
    public LRU(int capacity){
        this.capacity = capacity;
    }
    @Override
    public void add(String word){
        if (!words.isEmpty()){
            if(words.contains(word)){
                words.remove(word);
                words.add(word);}
            else
                words.add(word);
        }
        else
            words.add(word);
    }
    @Override
    public String remove() {
        Iterator<String> it = words.iterator();// Get the iterator
        return(it.next()); // return the first item
    }
}