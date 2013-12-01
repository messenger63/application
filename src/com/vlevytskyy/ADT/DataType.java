package com.vlevytskyy.ADT;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 25.10.13
 * Time: 2:11
 * To change this template use File | Settings | File Templates.
 */
public class DataType <T> implements IUserQueve, Iterable <T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private AtomicBoolean locker;
    private AtomicInteger lastIndex;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private T [] elements;
    private int [] priority;

    /*
    public DataType(Class<T> clazz,int capacity) {
        elements=(T[]) Array.newInstance(clazz, capacity);
    }
    */

    public DataType(){
        elements = (T[]) new Object[capacity];
        priority = new int[capacity];

        lastIndex = new AtomicInteger();
        lastIndex.set(0);

        locker = new AtomicBoolean();
        locker.set(false);
    }

    public DataType(int capacity){
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
        priority = new int[capacity];

        lastIndex = new AtomicInteger();
        lastIndex.set(0);

        locker = new AtomicBoolean();
        locker.set(false);
    }

    @Override
    public boolean isEmpty() {
        return lastIndex.get() == 0? true : false;
    }

    @Override
    public int size() {
        return lastIndex.get();
        //return capacity;
    }

    @Override
    public void addFirst(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
        while (true){
            if (locker.compareAndSet(false, true)){
                System.arraycopy(elements,0,elements,1, lastIndex.get() + 1);
                elements[0] = (T) o;
                lastIndex.incrementAndGet();
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void addLast(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
        while (true){
            if (locker.compareAndSet(false, true)){
                elements[lastIndex.get()] = (T) o;
                lastIndex.incrementAndGet();
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        while (true){
            if (locker.compareAndSet(false, true)){
                System.arraycopy(elements,1,elements,0, lastIndex.get());

                locker.set(false);
                return elements[0];
            }
        }
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        while (true){
            if (locker.compareAndSet(false, true)){
                elements[lastIndex.get()] = null;
                lastIndex.decrementAndGet();

                locker.set(false);
                return elements[lastIndex.get()];
            }
        }
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator <T> iterator() {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        return new Iterator<T>() {
            private int index = 0;
            private T current = elements[index];
            @Override
            public boolean hasNext() {
                if (current == null)
                    return false;
                return true;
                //return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException();
                T t = current;
                current = elements[index++];
                return t;
                //return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void remove() {
                //To change body of implemented methods use File | Settings | File Templates.
                throw new java.lang.UnsupportedOperationException();
            }
        };
    }
}