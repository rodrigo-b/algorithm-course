package algorithm.course.week3.interview;

public class Problem1 {

    public static int inversionsCount = 0;

    public static void main(String[] args) {

        int [] a = {10,6,30,16,20,25,18,28,19};

        int [] aux = new int [a.length];

        sort(a,aux,0,a.length -1);

        System.out.print("Total inversions = " + inversionsCount);

    }


    public static void sort(int [] a,int [] aux, int lo, int hi){

        if(hi <= lo)
            return;
        int mid =  (hi + lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux,mid + 1, hi);
        merge(a,aux,lo,mid,hi);
    }

    private static void merge(int[] a, int[] aux, int lo, int mid,int hi) {

        for(int i = lo; i <= hi; i++){
            aux[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;

        for(int k = lo; k <= hi; k++){

            if(i > mid){
                a[k] = aux[j++];
            } else if(j > hi){
                a[k] = aux[i++];
            }else if(aux[i] < aux[j]){
                a[k] = aux[i++];
            }else {
                a[k] = aux[j];
                if(i < j)
                    inversionsCount++;
                j++;

            }

        }

    }

    public static void countInversions(int iValue, int jValue){
        if(iValue > jValue)
            inversionsCount++;
    }

}
