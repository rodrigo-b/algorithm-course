package algorithm.course.week1.quickfind;

public class Main {

    public static void main(String[] args) {
        QuickUnionFind quickUnionFind = new QuickUnionFind(10);
        quickUnionFind.union(1,2);
        quickUnionFind.union(1,6);
        quickUnionFind.union(2,9);

        System.out.println(quickUnionFind.connected(1,2));
        System.out.println(quickUnionFind.connected(9,1));
        System.out.println(quickUnionFind.connected(6,2));

        System.out.println(quickUnionFind.find(1));


        quickUnionFind.union(3,0);
        quickUnionFind.union(0,5);
        quickUnionFind.union(7,5);
        quickUnionFind.union(5,4);
        quickUnionFind.union(5,8);

        System.out.println(quickUnionFind.connected(3,0));
        System.out.println(quickUnionFind.connected(5,3));
        System.out.println(quickUnionFind.connected(7,5));
        System.out.println(quickUnionFind.connected(3,4));


        System.out.println(quickUnionFind.find(3));
    }

}
