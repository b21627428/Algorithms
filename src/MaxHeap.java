import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int N;

    public MaxHeap(){
        this(new ArrayList<>());
    }

    public MaxHeap(List<T> heap) {
        this.heap = heap;
        N=heap.size();
    }

    public void insert(T key){
        heap.add(key);
        swim(++N);
    }
    private void swim(int k) {
        while(k>1 && less(k/2,k)){
            exchange(k/2,k);
            k/=2;
        }
    }
    private boolean less(int v, int w){
        return heap.get(v-1).compareTo(heap.get(w-1)) < 0;
    }
    private void exchange(int i,int j){
        T comparable = heap.get(i-1);
        heap.set(i-1,heap.get(j-1));
        heap.set(j-1,comparable);
    }
    public T delMax(){
        T max = heap.get(0);
        exchange(1,N--);
        sink(1,N);
        return max;
    }
    private void sink(int k,int N) {
        while(2*k<=N){
            int j=2*k;
            if(j<N && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exchange(k,j);
            k=j;
        }
    }

    public void sort(){
        for(int k=N/2; k>=1; k--){
            sink(k,N);
        }
        while(N>1){
            exchange(1,N);
            sink(1,--N);
        }
    }

    public List<T> getHeap() {
        return heap;
    }

    public void setHeap(List<T> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return "heap=" + heap;
    }
}
