public interface Search {

    static int binarySearch(Comparable[] a,Comparable key){
        return binarySearchHelper(a,key,0,a.length-1);
    }

    static int binarySearchHelper(Comparable[] a,Comparable key,int lo,int hi){
        if(lo<=hi){
            int mid=(lo+hi)/2;
            int cmp = a[mid].compareTo(key);
            if(cmp<0) return binarySearchHelper(a,key,mid+1,hi);
            else if(cmp>0) return binarySearchHelper(a,key,lo,mid-1);
            else return mid;
        }
        return -1;
    }
}
