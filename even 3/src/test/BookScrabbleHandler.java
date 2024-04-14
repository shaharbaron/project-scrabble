package test;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
    PrintWriter out;
    Scanner in;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        out=new PrintWriter(outToClient);
        in=new Scanner(inFromclient);
        String[] strings = in.next().split(",");
        String ask = strings[0];
        String[] array = new String[strings.length-1];
        System.arraycopy(strings, 1, array, 0, strings.length-1);
        if(ask.equals("Q")) {
            DictionaryManager dm = new DictionaryManager();
            if(dm.query(array))
                out.println("true");
            else
                out.println("false");
        }
        else {
            DictionaryManager dm = new DictionaryManager();
            if(dm.challenge(array))
                out.println("true");
            else
                out.println("false");
        }
        out.flush();
    }
    @Override
    public void close() {
        in.close();
        out.close();
    }
}
