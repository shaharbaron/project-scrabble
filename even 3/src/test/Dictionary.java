package test;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Dictionary {
    CacheManager cache1;
    CacheManager cache2;
    BloomFilter bloom;
    //static LinkedHashSet<String> hash = new LinkedHashSet<>();

    Dictionary (String...fileNames){
        CacheManager cache1=new CacheManager(400, new LRU());
        this.cache1 = cache1;
        CacheManager cache2=new CacheManager(100, new LFU());
        this.cache2 = cache2;
        BloomFilter bloom =new BloomFilter(256,"MD5","SHA1");
        this.bloom = bloom;
        LinkedHashSet<String> hash = new LinkedHashSet<>();
        for (int i = 0; i < fileNames.length; i++)
            hash.add(fileNames[i]);
        Iterator<String> it = hash.iterator();// Get the iterator
        while (it.hasNext()) {   try{
            File myObj = new File(it.next());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                bloom.add(myReader.next());
            }
            myReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    public boolean query (String word){
        if (cache1.words.contains(word))
            return true;
        else if (cache2.words.contains(word))
            return false;
        if (bloom.contains(word) == true){
            cache1.add(word);
            return true;}
        cache2.add(word);
        return false;
    }

    public boolean challenge (String word){
        if (IOSearcher.search(word)){
            cache1.add(word);
            return true;}
        else{
            cache2.add(word);
            return false;}
    }
}
