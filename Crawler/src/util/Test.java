package util;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Mahmood Neshati
 * Date: Feb 22, 2015
 * Time: 2:39:34 PM
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> p= new ArrayList<Integer>();
        for(int i=0;i<19;i++){
            p.add(i+1) ;
         
        }
        java.util.Collections.shuffle(p);
        System.out.println(p);

    }
}
