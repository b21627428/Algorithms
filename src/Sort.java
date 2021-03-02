import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Sort{

    //HELPER FUNCTIONS
    static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    static boolean lessThanEqual(Comparable v,Comparable w){
        return v.compareTo(w) <=0;
    }
    static void exchange(Comparable[] a, int i,int j){
        Comparable comparable = a[i];
        a[i] = a[j];
        a[j] = comparable;
    }
    static void shuffle(Comparable[] a){
        List<Comparable> list = Arrays.asList(a);
        Collections.shuffle(list);
        list.toArray(a);
    }

    //INSERTION SORT
    static void insertionSort(Comparable[] a){
        int N=a.length;
        for(int i=0; i<N; i++){
            for(int j=i; j>0; j--){
                if(less(a[j],a[j-1]))
                    exchange(a,j,j-1);
                else break;
            }
        }
    }

    //SELECTION SORT
    static void selectionSort(Comparable[] a){
        int N = a.length;
        for (int i=0; i<N; i++){
            int min=i;
            for (int j=i+1; j<N; j++){
                if(less(a[j],a[min]))
                    min=j;
            }
            exchange(a,i,min);
        }
    }

    //SHELL SORT
    static void shellSort(Comparable[] a){
        int N=a.length;
        int h=1;
        while(h<N/3) h= 3*h +1;
        while(h>=1){
            for(int i=h; i<N; i++){
                for(int j=i; j>=h && Sort.less(a[j],a[j-h]);  j-=h){
                    Sort.exchange(a,j,j-h);
                }
            }
            h/=3;
        }
    }

    //MERGE SORT
    static void mergeSort(Comparable[] a){
        inplaceMergeSort(a);
    }
    static void mergeSort(Comparable[] a,boolean inplace){
        if(inplace) inplaceMergeSort(a);
        else outplaceMergeSort(a);
    }

    //OUTPLACE MERGE SORT
    static void outplaceMergeSort(Comparable[] a) {
        if(a.length>1){
            int mid=a.length/2;
            Comparable[] left = new Comparable[mid];
            Comparable[] right = new Comparable[a.length - mid];
            for(int i=0; i<mid; i++){
                left[i] = a[i];
            }
            for (int i=mid; i<a.length; i++){
                right[i-mid] = a[i];
            }
            outplaceMergeSort(left);
            outplaceMergeSort(right);
            outplaceMergeSortHelperMerge(a,left,right,mid,a.length-mid);
        }
    }
    static void outplaceMergeSortHelperMerge(Comparable[] a, Comparable[] left, Comparable[] right, int leftIndex, int rightIndex) {
        int i=0,j=0,k=0;
        while(i<leftIndex || j<rightIndex){
            if (i>=leftIndex) a[k++] = right[j++];
            else if(j>=rightIndex) a[k++] = left[i++];
            else if(less(left[i],right[j])) a[k++] = left[i++];
            else a[k++] = right[j++];
        }
    }


    //INPLACE MERGE SORT
    static void inplaceMergeSort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        mergeSortHelperSort(a,aux,0,a.length-1);
    }
    static void mergeSortHelperSort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(lo<hi){
            int mid = (lo+hi)/2;
            mergeSortHelperSort(a,aux,lo,mid);
            mergeSortHelperSort(a,aux,mid+1,hi);
            mergeSortHelperMerge(a,aux,lo,mid,hi);
        }
    }
    static void mergeSortHelperMerge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi){
        for(int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }
        int i=lo, j=mid+1;
        for(int k=lo; k<=hi; k++){
            if(i>mid) a[k] = aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else  a[k] = aux[i++];
        }
    }

    //BOTTOMUP MERGE SORT - NO RECURSION
    static void bottomUpMergeSort(Comparable[] a){
        int N= a.length;
        Comparable[] aux = new Comparable[N];
        for(int sz=1; sz<N; sz*=2){
            for(int lo=0; lo<N-sz; lo+= 2*sz){
                mergeSortHelperMerge(a,aux,lo,lo+sz-1,Math.min(lo+2*sz-1,N-1));
            }
        }
    }

    //QUIK SORT
    static void quickSort(Comparable[] a,boolean threeWay){
        shuffle(a);
        if(!threeWay){
            quickSortHelperSort(a,0,a.length-1);
        }
        else{
            quickSort3Way(a,0,a.length-1);
        }
    }
    static void quickSortHelperSort(Comparable[] a,int low,int high){
        if(low<high){
            int pivot= quickSortHelperPartition(a,low,high);
            quickSortHelperSort(a,low,pivot-1);
            quickSortHelperSort(a,pivot+1,high);
        }
    }
    static int quickSortHelperPartition(Comparable[] a, int low, int high) {
        Comparable pivot= a[high];
        int i=low-1;
        for(int j=low; j<high; j++){
            if(lessThanEqual(a[j],pivot)){
                i++;
                exchange(a,i,j);
            }
        }
        exchange(a,i+1,high);
        return (i+1);
    }
    static void quickSort3Way(Comparable[] a,int lo,int hi){
        if(lo<hi){
            int i=lo;
            int lt=lo;
            int gt=hi;
            Comparable v = a[lo];
            while(i<=gt){
                int cmp = a[i].compareTo(v);
                if(cmp<0) exchange(a,lt++,i++);
                else if(cmp>0) exchange(a,i,gt--);
                else i++;
            }
            quickSort3Way(a,lo,lt-1);
            quickSort3Way(a,gt+1,hi);
        }
    }

}
