package algorithm.course.week3;

public class OwnMergeSort {

    public static void main(String[] args) {

        int [] array = {10,4,2,5,6,7,0,1,8};
        sort(array);

        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]);
        }
    }



    public static void sort(int [] array){

        int [] aux = new int[array.length];
        int ini = 0;
        int last = array.length;

        sort(array,aux, ini, last);


    }

    private static void sort(int[] array, int[] aux, int ini, int last) {

        if( last <= ini)
            return;

        int mid = (ini + last) / 2;

        sort(array, aux, ini, mid);
        sort(array, aux, mid+1, last);
        merge(array, aux, ini, mid, last);
    }

    private static void merge(int[] array, int[] aux, int ini, int mid, int last) {

        for(int i = ini; i <= last; i++){
            aux[i] = array[i];
        }








    }

}
