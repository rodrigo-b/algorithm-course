package algorithm.course.week1.quickfind;

import java.io.Serializable;

public class QuickUnionFind {

    private int [] arrayOfIntegers;
    private int [] arrayToCountTotalElements;
    private int [] graterValueOfComponent;

    public QuickUnionFind(int totalOfElements){
        this.arrayOfIntegers = new int[totalOfElements];
        this.arrayToCountTotalElements = new int[totalOfElements];
        this.graterValueOfComponent = new int[totalOfElements];
        for(int i = 0; i< arrayOfIntegers.length; i++){
            arrayOfIntegers[i] = i;
            arrayToCountTotalElements[i] = 1;
            graterValueOfComponent[i] = 1;
        }
    }

    public int find(int value){

        int root = root(value);
        return graterValueOfComponent[root];
    }

    public boolean connected(int value1,int value2){

        int root1 = root(value1);
        int root2 = root(value2);

        if(root1 == root2){
            return true;
        }

        return false;
    }

    public void union(int value1, int value2){

        int root1 = root(value1);
        int root2 = root(value2);


        if(root1 == root2)
            return;

        if(arrayToCountTotalElements[root1] > arrayToCountTotalElements[root2]){
            arrayOfIntegers[root2] = root1;
            arrayToCountTotalElements[root1] += arrayToCountTotalElements[root2];

        }else{
            arrayOfIntegers[root1] = root2;
            arrayToCountTotalElements[root2] += arrayToCountTotalElements[root1];
        }

        if(value1 > value2 && value1 > graterValueOfComponent[arrayOfIntegers[root1]])
            graterValueOfComponent[arrayOfIntegers[root1]] = value1;
        else if(value2 > value1 && value2 > graterValueOfComponent[arrayOfIntegers[root1]] )
            graterValueOfComponent[arrayOfIntegers[root1]] = value2;

    }

    private int root(int value){

        while (arrayOfIntegers[value] != value){
            arrayOfIntegers[value] = arrayOfIntegers[arrayOfIntegers[value]];
            value = arrayOfIntegers[value];
        }

        return value;
    }


}
