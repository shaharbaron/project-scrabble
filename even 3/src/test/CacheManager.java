package test;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class CacheManager{
    int size;
    LinkedHashSet<String> words = new LinkedHashSet<String>();
    CacheReplacementPolicy crp;
    CacheManager(int size, CacheReplacementPolicy crp){
        this.size = size;
        this.crp = crp;
    }
    public boolean query(String word){
        return words.contains(word);
    }
    public void add(String word){
            crp.add(word);
            words.add(word);
        if (words.size() > size)
            words.remove(crp.remove());
    }

    public String remove(){
        return crp.remove();
    }
}