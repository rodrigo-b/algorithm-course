package algorithm.course.week4.questsinterview.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Stack;

public class Solver {

    private int moves;
    private boolean isSolvable = true;
    private Stack<Board> solutionBoards;
    private boolean isGoal;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){

        if(initial == null){
            throw new IllegalArgumentException();
        }
        solutionBoards = new Stack<>();
        moves = 0;
        Comparator<Node> manhattanComparator = (o1, o2) -> (o1.getPriority() - o2.getPriority());
        MinPQ<Node> pq = new MinPQ<>(manhattanComparator);
        pq.insert(new Node(initial));

        MinPQ<Node> twinPQ = new MinPQ<>(manhattanComparator);
        twinPQ.insert(new Node(initial.twin()));

        while(!pq.min().getCurrentBoard().isGoal() && !twinPQ.min().getCurrentBoard().isGoal()){
            checkNeighbor(pq);
            checkNeighbor(twinPQ);
        }

        isSolvable = pq.min().getCurrentBoard().isGoal();

        if(isSolvable){
            Node currentNode = pq.min();

            solutionBoards.push(currentNode.getCurrentBoard());
            while (currentNode.getPreviousSearchNode() != null) {
                currentNode = currentNode.getPreviousSearchNode();
                solutionBoards.push(currentNode.getCurrentBoard());
            }
        }

    }

    private void checkNeighbor(MinPQ<Node> pq) {
        Node minNode = pq.delMin();

        for (Board neighbor : minNode.getCurrentBoard().neighbors()) {

            if(minNode.getPreviousSearchNode() != null && neighbor.equals(minNode.getPreviousSearchNode().getCurrentBoard()))
                    continue;

            Node node = new Node(neighbor, minNode, (minNode.moves + 1));
            pq.insert(node);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return solutionBoards.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        return isSolvable? solutionBoards: null;
    }

    // test client (see below)
    public static void main(String[] args){

        int [][] boardTile = {
                {1,2,3},
                {4,5,6},
                {8,7,0}
        };


        Board initialBoard = new Board(boardTile);
        Solver solver = new Solver(initialBoard);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class Node{

        private Board currentBoard;
        private Node previousSearchNode;
        private int moves;
        private int priority;

        public Node(Board initialBoard) {
            this.currentBoard = initialBoard;
            this.previousSearchNode = null;
            moves = 0;
            this.priority = initialBoard.manhattan() + moves;
        }

        public Node(Board initialBoard, Node previousSearchNode, int moves) {
            this.currentBoard = initialBoard;
            this.previousSearchNode = previousSearchNode;
            this.moves = moves;
            this.priority = initialBoard.manhattan() + moves;
        }

        public Board getCurrentBoard() {
            return currentBoard;
        }

        public Node getPreviousSearchNode() {
            return previousSearchNode;
        }

        public int getPriority() {
            return priority;
        }
    }

}
