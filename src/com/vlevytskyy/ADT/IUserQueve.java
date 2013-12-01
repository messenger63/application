package com.vlevytskyy.ADT;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Artist
 * Date: 13.11.13
 * Time: 0:33
 * To change this template use File | Settings | File Templates.
 */
public interface IUserQueve <Item>{
    //public Deque()                    // construct
    public boolean isEmpty();           // is the deque empty?
    public int size();                  // return the number of
    public void addFirst(Item item);    // insert the item at the front
    public void addLast(Item item);     // insert the item at the end
    public Item removeFirst();          // delete and return
    public Item removeLast();           // delete and return
    public Iterator<Item> iterator();   // return an iterator over items
}
