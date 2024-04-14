package test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DictionaryManager extends Dictionary {

    Map<String, Dictionary> mapa;
    private static DictionaryManager dictionary = null;

    DictionaryManager() {
        mapa=new HashMap<String, Dictionary>();
    }

    public boolean query (String...args){
        boolean t= false;
        int len = args.length;
        for (int i=0; i < args.length-1; i++){
            if (!mapa.containsKey(args[i]))
                mapa.put(args[i], new Dictionary(args[i]));
            if(mapa.get(args[i]).query(args[len-1]))
                return true;
        }
        return false;
    }
    public boolean challenge (String...args){
        int len = args.length;
        for (int i=0; i < args.length-1; i++){
            if (!mapa.containsKey(args[i]))
                mapa.put(args[i], new Dictionary());
            try{
                File myObj = new File(args[i]);
                Scanner myReader = new Scanner(myObj);
                String data = myReader.findInLine(args[len-1]);
                if (data != null)
                    return true;
                myReader.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getSize (){
        return mapa.size();
    }

    public static DictionaryManager get() {
        if (dictionary == null)
            dictionary = new DictionaryManager();
        return dictionary;
    }

}
