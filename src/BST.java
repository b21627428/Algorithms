import java.util.LinkedList;
import java.util.Queue;

public class BST<K extends Comparable<K>,V>{

    private class Node{
        private K key;
        private V value;
        private Node left,right;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
        }
    }
    private Node root;

    public BST() {
        this.root = null;
    }

    public void put(K key,V value){
        root = put(root,key,value);
    }
    private Node put(Node x,K key,V value){
        if(x==null) return new Node(key,value);
        int cmp = key.compareTo(x.key);
        if(cmp<0) x.left = put(x.left,key,value);
        else if(cmp>0) x.right = put(x.right,key,value);
        else x.value = value;
        return x;
    }
    public V get(K key){
        Node x=root;
        while (x!=null){
            int cmp = key.compareTo(x.key);
            if(cmp<0) x=x.left;
            else if(cmp>0) x=x.right;
            else return x.value;
        }
        return null;
    }
    public Iterable<K> keys(){
        Queue<K> keys = new LinkedList<>();
        inOrder(root,keys);
        return keys;
    }
    public Iterable<V> values(){
        Queue<V> values = new LinkedList<>();
        inOrderValues(root,values);
        return values;
    }
    public void delete(K key){
        root = delete(root,key);
    }

    //HIBBARD DELETION
    private Node delete(Node x, K key) {
        if(x==null) return null;
        int cmp= key.compareTo(x.key);
        if(cmp<0) x.left=delete(x.left,key);
        else if(cmp>0) x.right=delete(x.right,key);
        else{
            if(x.right==null) return x.left;
            else{
                Node t=x;
                x=minNode(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        return x;
    }


    private void deleteMin(){
        root=deleteMin(root);
    }
    private Node deleteMin(Node x) {
        if(x==null) return null;
        else if(x.left ==null) return x.right;
        else x.left=deleteMin(x.left);
        return x;
    }
    private Node minNode(Node x){
        if(x==null) return null;
        else if(x.left==null) return x;
        else return minNode(x.left);
    }
    private void inOrder(Node x, Queue<K> keys) {
        if(x==null) return;
        inOrder(x.left,keys);
        keys.add(x.key);
        inOrder(x.right,keys);
    }
    private void inOrderValues(Node x,Queue<V> values){
        if(x==null) return;
        inOrderValues(x.left,values);
        values.add(x.value);
        inOrderValues(x.right,values);
    }

}
