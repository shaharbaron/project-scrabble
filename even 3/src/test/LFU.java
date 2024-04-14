package test;
import java.lang.reflect.Array;
import java.util.*;

public class LFU implements CacheReplacementPolicy {
    ArrayList<Word> words = new ArrayList<Word>();
    public LFU() {
        this.words = words;
    }
    public class Word{
        String mila;
        int count=0;
        Word(){
            this.mila = null;
            this.count = 0;
        }
        Word (String mila, int count){
            this.mila = mila;
            this.count = count;
        }
        public String getMila() {
            return mila;}

        public int getCount() {
            return count;}
    }
    @Override
    public void add(String word){
        if (words.isEmpty()) {
            words.add(new Word(word, +1));
            return;
        }
        for (Word w : words){
            if (w.mila == word) {
                w.count++;
                return;
                }
            }
        words.add((new Word(word,+1)));
    }
    @Override
    public String remove(){
        Word temp = new Word();
        int i,j;
        for (i=0; i < words.size();i++){
            for (j=i+1 ; j < words.size(); j++) {
                if (words.get(i).count > words.get(j).count) {
                    temp.mila = words.get(i).mila;
                    temp.count = words.get(i).count;
                    words.get(i).mila = words.get(j).mila;
                    words.get(i).count = words.get(j).count;
                    words.get(j).mila = temp.mila;
                    words.get(j).count = temp.count;
                }
            }
        }
        return words.get(0).mila;
    }
}
