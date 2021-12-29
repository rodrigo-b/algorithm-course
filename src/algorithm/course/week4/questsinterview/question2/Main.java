package algorithm.course.week4.questsinterview.question2;

public class Main {

    public static void main(String[] args) {

        BinaryHeap<Integer> keyBinaryHeap = new BinaryHeap<>(10);

        keyBinaryHeap.insert(1);
        keyBinaryHeap.insert(7);
        keyBinaryHeap.insert(4);
        keyBinaryHeap.insert(9);


        keyBinaryHeap.delMax();

        System.out.println(keyBinaryHeap.sample());

        System.out.println(keyBinaryHeap.delRandom());

        System.out.println(keyBinaryHeap.sample());
    }

}
