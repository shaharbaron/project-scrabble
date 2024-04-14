package test;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.*;
import java.io.*;

import static java.lang.Math.abs;

public class BloomFilter {
    int length;
    BitSet bitset = new BitSet(length);
    LinkedHashSet<String> hash = new LinkedHashSet<>();
    public BloomFilter(int length, String... algs) {
        this.length = length;
        for (int i = 0; i < algs.length; i++)
            hash.add(algs[i]);
    }

    public void add(String word) {
        Iterator<String> it = hash.iterator();// Get the iterator
        while (it.hasNext()) {
            try {
                MessageDigest md = MessageDigest.getInstance(it.next());
                byte[] bts = md.digest(word.getBytes());
                int a = new BigInteger(bts).intValue();
                a = a % length;
                bitset.set(abs(a));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean contains(String word) {
        int[] array = bitset.stream().toArray();
        Iterator<String> it = hash.iterator();// Get the iterator
        while (it.hasNext()) {
            try {
                MessageDigest md = MessageDigest.getInstance(it.next());
                byte[] bts = md.digest(word.getBytes());
                int a = new BigInteger(bts).intValue();
                a = a % length;
                if (!search(abs(a),array,0,array.length-1))
                    return false;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean search (int num , int[] array, int left, int right){
        int middle = (left + right)/2;
        if (left > right)
            return false;
        if (array[middle] == num)
            return true;
        if (array[middle] > num)
            return search(num, array,left,middle-1);
        if (array[middle] < num)
            return search(num, array, middle+1, right);
        return false;
    }

    @Override
    public String toString() {
        int count=0, length =0;
        int[] array = bitset.stream().toArray();
        StringBuilder str = new StringBuilder();
        while (count < array.length){
            while (length != array[count]){
                str.append(0);
                length++;}
            str.append(1);
            length++;
            count++;}
        String b = str.toString();
        return b;
    }
}