package com.vlevytskyy.ADT;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 25.10.13
 * Time: 2:10
 * To change this template use File | Settings | File Templates.
 */
public interface IUserArray <Item>{
    //public Deque()                        // construct
    public boolean isEmpty();               // is the deque empty?
    public int size();                      // return the number of
    public boolean contains(Item item);
    public void clear();
    public void trimToSize();
    public int indexOf(Item item);
    public Item get(int index);
    public void set(int index, Item item);
    public void add(Item item);             // insert the item at the front
    public void add(int index, Item item);
    public void remove(int index);
    public void remove(Item item);          // delete and return
    public Iterator<Item> iterator();       // return an iterator over items
}

