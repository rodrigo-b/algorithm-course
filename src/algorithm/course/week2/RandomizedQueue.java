package algorithm.course.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue <Item> implements Iterable<Item>{

    private int size = 0;
    private Item [] items = (Item[]) new Object[1];

    // construct an empty randomized queue
    public RandomizedQueue(){
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){

        if(item == null)
            throw new IllegalArgumentException();

        if(size == items.length){
            resize(2 * items.length);
        }

        items[size] = item;
        size++;
    }

    private void resize(int capacity) {

        Item[] itemsCopy = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++){
            itemsCopy[i] = items[i];
        }
        items = itemsCopy;
        itemsCopy = null;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty())
            throw new NoSuchElementException();

        int randomizedPosition = StdRandom.uniform(size);

        if(size == items.length / 4){
            resize(items.length / 2);
        }

        Item item = items[randomizedPosition];

        if(randomizedPosition == size - 1){
            items[randomizedPosition] = null;
        }else {
            items[randomizedPosition] = items[size - 1];
            items[size - 1] = null;
        }

        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){

        if(isEmpty())
            throw new NoSuchElementException();

        int randomizedPosition = StdRandom.uniform(size);

        return items[randomizedPosition];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private Item [] randomizedArray = (Item[]) new Object[size];

        private int current = 0;

        public ListIterator(){

            for(int i = 0; i < size; i++){
                randomizedArray[i] = items[i];
            }
            StdRandom.shuffle(randomizedArray);
        }


        @Override
        public boolean hasNext() {

            if(current < size)
                return true;

            return false;
        }

        @Override
        public Item next() {

            if(!hasNext())
                throw new NoSuchElementException();

            return randomizedArray[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        StdOut.println(randomizedQueue.isEmpty());

        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);


        StdOut.println("QueueSize = " + randomizedQueue.size());

        StdOut.println("QueueSample = " + randomizedQueue.sample());

        StdOut.println(randomizedQueue.isEmpty());

        StdOut.println("DequeuedItem = " + randomizedQueue.dequeue());

        StdOut.println("QueueSize = " + randomizedQueue.size());

        Iterator<Integer> iterator = randomizedQueue.iterator();

        while(iterator.hasNext()){

            Integer next = iterator.next();
            StdOut.println("Randomized Value = " + next);
        }



    }

}
