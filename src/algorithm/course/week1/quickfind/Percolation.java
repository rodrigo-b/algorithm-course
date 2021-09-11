package algorithm.course.week1.quickfind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//Test pass on submit ,but need improve the memory that it consumes.
public class Percolation {

    private final int [][] sitesMatriz;
    private boolean [] openedSites;
    private final int numberOfIndices;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF indicesRelation;
    // more one to solve backwash problem
    private WeightedQuickUnionUF indicesRelationOnlyTopSite;
    private final int topSite;
    private final int bottomSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if(n <= 0) {
            throw new IllegalArgumentException();
        }

        indicesRelation = new WeightedQuickUnionUF((n * n) + 2);
        indicesRelationOnlyTopSite = new WeightedQuickUnionUF((n * n) + 2);
        numberOfIndices = n;

        topSite = n * n;
        bottomSite = n * n + 1;

        openedSites = new boolean[n * n];

        sitesMatriz = new int[numberOfIndices][numberOfIndices];

        int contador = 0;
        for(int i = 0; i < sitesMatriz.length; i++) {
            for(int j = 0; j < sitesMatriz[i].length; j++) {
                sitesMatriz[i][j] = contador;
                contador++;
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if(row <= 0 || col <= 0 || row > numberOfIndices || col > numberOfIndices)
            throw new IllegalArgumentException();

        row = row - 1;
        col = col - 1;

        int indice = sitesMatriz[row][col];

        if(!openedSites[indice]) {

            openedSites[indice] = true;
            numberOfOpenSites++;

            int beforeRow = row - 1;
            if (beforeRow >= 0) {
                int openedIndice = sitesMatriz[beforeRow][col];
                if(openedSites[openedIndice]) {
                    indicesRelation.union(sitesMatriz[row][col], sitesMatriz[beforeRow][col]);
                    indicesRelationOnlyTopSite.union(sitesMatriz[row][col], sitesMatriz[beforeRow][col]);
                }
            }

            int afterRow = row + 1;
            if (afterRow < sitesMatriz.length) {

                int openedIndice = sitesMatriz[afterRow][col];
                if(openedSites[openedIndice]) {
                    indicesRelation.union(sitesMatriz[row][col], sitesMatriz[afterRow][col]);
                    indicesRelationOnlyTopSite.union(sitesMatriz[row][col], sitesMatriz[afterRow][col]);
                }
            }

            int beforeCol = col - 1;
            if (beforeCol >= 0 ) {

                int openedIndice = sitesMatriz[row][beforeCol];
                if(openedSites[openedIndice]) {
                    indicesRelation.union(sitesMatriz[row][col], sitesMatriz[row][beforeCol]);
                    indicesRelationOnlyTopSite.union(sitesMatriz[row][col], sitesMatriz[row][beforeCol]);
                }
            }

            int afterCol = col + 1;
            if (afterCol < sitesMatriz.length ) {
                int openedIndice = sitesMatriz[row][afterCol];
                if(openedSites[openedIndice]) {
                    indicesRelation.union(sitesMatriz[row][col], sitesMatriz[row][afterCol]);
                    indicesRelationOnlyTopSite.union(sitesMatriz[row][col], sitesMatriz[row][afterCol]);
                }
            }


            if(indice < numberOfIndices) {
                indicesRelation.union(indice,topSite);
                indicesRelationOnlyTopSite.union(indice,topSite);
            }

            if(indice >=  (numberOfIndices  * numberOfIndices) - numberOfIndices && indice < (numberOfIndices * numberOfIndices)) {
                indicesRelation.union(indice,bottomSite);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if(row <= 0 || col <= 0 || row > numberOfIndices || col > numberOfIndices)
            throw new IllegalArgumentException();

        row = row - 1;
        col = col - 1;

        int indice = sitesMatriz[row][col];

        if(openedSites[indice])
            return true;

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        if(row <= 0 || col <= 0 || row > numberOfIndices || col > numberOfIndices)
            throw new IllegalArgumentException();

        row = row - 1;
        col = col - 1;

        int rootTopSite = indicesRelationOnlyTopSite.find(topSite);
        int indice = sitesMatriz[row][col];
        int indiceRoot = indicesRelationOnlyTopSite.find(indice);

        return rootTopSite == indiceRoot;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        int rootTopSite = indicesRelation.find(topSite);
        int rootBottomSite = indicesRelation.find(bottomSite);

        return rootTopSite == rootBottomSite;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);

        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        //percolation.open(4,1);
        //percolation.open(-1,5);


        System.out.println(percolation.isFull(3,1));


    }

}
