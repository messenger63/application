package com.vlevytskyy.ADT;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 13.11.13
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
public class ArrayDataType <T> implements IUserArray, Iterable <T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 12;

    private AtomicBoolean locker;
    private AtomicInteger lastIndex;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private T [] elements;
    private int [] priority;

    public ArrayDataType(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayDataType(int capacity){
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
        priority = new int[capacity];

        lastIndex = new AtomicInteger();
        lastIndex.set(-1);

        locker = new AtomicBoolean();
        locker.set(false);
    }

    @Override
    public boolean isEmpty() {
        return lastIndex.get() < 0;
    }

    @Override
    public int size() {
        return lastIndex.get() + 1;
    }

    @Override
    public boolean contains(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        while (true){
            if (locker.compareAndSet(false,true)){
                lastIndex.set(0);
                elements = (T[]) new Object[capacity];
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void trimToSize() {
        while (true){
            if (locker.compareAndSet(false,true)){
                capacity = (lastIndex.get() / 3) * 4;
                T [] tempElements = (T[]) new Object[capacity];
                System.arraycopy(elements, 0, tempElements, 0, lastIndex.get());
                elements = tempElements;
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public int indexOf(Object o) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < lastIndex.get(); i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > lastIndex.get())
            throw new ArrayIndexOutOfBoundsException();
        return elements[index];
    }

    @Override
    public void set(int index, Object o) {
        while (true) {
            if (locker.compareAndSet(false, true)) {
                elements[index] = (T) o;
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void add(Object o) {
        while (true){
            if (locker.compareAndSet(false, true)){

                if (capacity-1 == lastIndex.get()){
                    T [] newElements = (T[]) new Object[capacity * 2];
                    System.arraycopy(elements, 0, newElements, 0, capacity);
                    elements = newElements;
                    capacity *= 2;
                }

                elements[lastIndex.incrementAndGet()] = (T) o;

                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void add(int index, Object o) {
        while (true) {
            if (locker.compareAndSet(false, true)) {
                System.arraycopy(elements, index, elements, index + 1, lastIndex.get() - index);
                elements[index] = (T) o;
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if (index > lastIndex.get()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (true) {
            if (locker.compareAndSet(false, true)) {
                if (index == lastIndex.get()) {
                    lastIndex.decrementAndGet();
                } else {
                    System.arraycopy(elements, index + 1, elements, index, lastIndex.getAndDecrement() - index);
                }
                locker.set(false);
                return;
            }
        }
    }

    @Override
    public void remove(Object o) {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        for (int i = 0; i < lastIndex.get(); i++) {
            if (elements[i].equals(o)) {
                while (true) {
                    if (locker.compareAndSet(false, true)) {
                        if (elements[i].equals(o)) {
                            if (i == lastIndex.get()) {
                                lastIndex.decrementAndGet();
                            } else {
                                System.arraycopy(elements, i + 1, elements, i, lastIndex.getAndDecrement() - i);
                            }
                            locker.set(false);
                            return;
                        }
                    }
                }
            }
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int last = lastIndex.get();
            @Override
            public boolean hasNext() {
                return index < last;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException();
                return ArrayDataType.this.elements[index++];
            }

            @Override
            public void remove() {
                ArrayDataType.this.remove(index);
            }
        };
    }
}