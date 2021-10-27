package algorithm.course.week2;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private int size = 0;


    // construct an empty deque
    public Deque () {

    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){

        if(item == null)
            throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;

        if(first == null && last == null) {
            this.last = newNode;
        }else{
            this.first.prev = newNode;
        }

        this.first = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item){

        if(item == null)
            throw new IllegalArgumentException();

        Node newNode = new Node();
        newNode.prev = this.last;
        newNode.item = item;

        if(first == null && last == null){
            this.first = newNode;
        }else{
            this.last.next = newNode;
        }

        this.last = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){

        if(isEmpty())
            throw new IllegalArgumentException();

        Item item = this.first.item;

        if (first.next == null) {
            last = null;
        }
        else {
            first.next.prev = null;
        }

        this.first = this.first.next;
        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){

        if(isEmpty())
            throw new IllegalArgumentException();

        Item item = this.last.item;

        if (last.prev == null) {
            first = null;
        }
        else {
            last.prev.next = null;
        }

        this.last = this.last.prev;
        size--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class Node {
        public Item item;
        public Node next;
        public Node prev;

    }

    private class ListIterator implements Iterator<Item>{

        private Node current = first;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {


            if(current == null){
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;


            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }


    }


    // unit testing (required)
    public static void main(String[] args){

        Deque<Integer> integers = new Deque<>();

        integers.addFirst(10);
        integers.addFirst(9);
        integers.addFirst(8);

        integers.addLast(11);
        integers.addLast(12);

        integers.removeFirst();
        integers.removeLast();

        Iterator<Integer> iterator = integers.iterator();



        while(iterator.hasNext()){

            Integer next = iterator.next();
            StdOut.println("iterator Value = " + next);
        }

    }
}
