package com.vlevytskyy.ADT;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 27.10.13
 * Time: 2:45
 * To change this template use File | Settings | File Templates.
 */
public class StartApplication {

    public static int N = 1000000;

    static long timeStart;
    static long time;

    public static void main (String [] args) throws NoSuchFieldException, IllegalAccessException {

        //ArrayDataType<Double> arrayDataType = new ArrayDataType<Double>(10000000);
        //List<Double> arrayDataType = Collections.synchronizedList(new ArrayList<Double>(10000000));
        List<Double> arrayDataType = new ArrayList<Double>(10000000);

        arrayDataType.add(null);


        for (int i = 0; i < N; i++) {
            arrayDataType.add(Math.random());
        }



        timeStart = System.nanoTime();




        time = System.nanoTime() - timeStart;

        System.out.println("Size: " + N);
        System.out.println("Time: " + time);

    }


}
