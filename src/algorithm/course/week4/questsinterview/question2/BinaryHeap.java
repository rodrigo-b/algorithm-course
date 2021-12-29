package algorithm.course.week4.questsinterview.question2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.PriorityQueue;

public class BinaryHeap <Key extends Comparable<Key>>{

    private Key [] itens;
    private int totalItens;

    public BinaryHeap(final int capacity) {
        itens = (Key []) new Comparable[capacity + 1];
    }

    /**
        bottomUp , when child k is grater than the parent key, is a violation of Binary Heap Tree

     */
    private void swim(int position){
        while(position > 1 && less((position/2), position)){
            exchange((position /2), position);
            position = position / 2;
        }
    }

    public void insert(Key value){
        itens[++totalItens] = value;
        swim(totalItens);
    }

    public Key sample(){
        int randomPosition = StdRandom.uniform(1, totalItens + 1);
        return itens[randomPosition];
    }


    public Key delRandom(){
        int randomPosition = StdRandom.uniform(1, totalItens + 1);
        Key key = itens[randomPosition];
        exchange(randomPosition, totalItens--);
        sink(1);
        itens[totalItens + 1] = null;
        return key;
    }


    public Key delMax(){
        Key max = itens[1];
        exchange(1, totalItens--);
        sink(1);
        itens[totalItens + 1] = null;
        return max;
    }

    private boolean less(final int firstPosition,final int position){
        return itens[firstPosition].compareTo(itens[position]) < 0;
    }

    private void exchange(final int parentPostion,final int position){
        Key oldParentValue = itens[parentPostion];
        itens[parentPostion] = itens[position];
        itens[position] = oldParentValue;
    }


    private void sink(int position){

        while( (2*position) <= totalItens){
            int child = (2 * position);

            if(child < totalItens && less(child, child + 1))
                child++;

            if(!less(position, child))
                break;

            exchange(position, child);
            position = child;
        }

    }


}
