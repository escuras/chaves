/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import TimeDate.WeekDay;
import java.util.Arrays;
import java.util.BitSet;
import java.util.function.Predicate;

/**
 *
 * @author toze
 */
public class Teste4 {

    public static void main(String[] args) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) {
                bits1.set(i);
            }
            if ((i % 5) != 0) {
                bits2.set(i);
            }
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);
        int[] arra = {1,2,4,6,7,8,3,4,23,45,455};
        String[] arra2 = {"1","i","4","6","7","8","3","4","23","45","455"};
        for (String g : arra2) {
            System.out.println(g);
        }
        System.out.println(Arrays.binarySearch(arra, 45));
        TimeDate.Date sat = new TimeDate.Date(29,4,1978);
        System.out.println(WeekDay.getDayWeek(sat));
        int h = 0;
        int f = 1;
        if ((h  == 0 )||(f == 1)) {
            System.out.println("ddddddd");
        } else {
            System.out.println("fffffffff");
        }
        
        System.out.println("ola");
        midle:
        for (int n = 0; n < 5; n++) {
            fo:
            for (int m = 0; m < 5; m++) {
                System.out.println(n);
            if (m == 4) break midle;
            }
        }
        
        System.out.print("principal ");
        System.out.println(Thread.currentThread().getId());
        
        Runnable r  = () -> {
            System.out.print("ola ");
            System.out.println(Thread.currentThread().getId());
        };
        Thread t = new Thread(r);
        t.start();
        Predicate<String> pred;
        String g = "ola";
        System.out.println();
        Langs.Locale lingua = new Langs.Locale();
        Object[] lista = lingua.linguas.toArray();
        java.util.List teste = lingua.getlist(4);
        for (int i = 0; i < lista.length; i++ ){
            System.out.println(lista[i].toString());
            System.out.println("");
            System.out.println(teste.get(i));
        }
    }

    static int binarySearch(int[] array, int value, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] == value) {
            return middle;
        } else if (array[middle] > value) {
            return binarySearch(array, value, left, middle - 1);
        } else {
            return binarySearch(array, value, middle + 1, right);
        }
    }
}