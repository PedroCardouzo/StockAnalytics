package Tree;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Node<V extends Comparable<V>, D>{
    private V value;
    private D data;
    private Node<V, D> left;
    private Node<V, D> right;

    public Node(V value){
        this(value, null);
    }

    public Node(V value, D data){
        this.setValue(value);
        this.setData(data);
        this.left = null;
        this.right = null;
    }

    public void setValue(V value){
        this.value = value;
    }

    public void setData(D data) {
        this.data = data;
    }

    public void insert(Node<V, D> n){
        if(n.getValue().compareTo(this.getValue()) > 0) {
            if (this.right == null)
                this.right = n;
            else
                this.right.insert(n);
        }else{
            if(this.left == null)
                this.left = n;
            else
                this.left.insert(n);
        }

    }

    public Pair<V, D> find(V value){
        if(value.equals(this.getValue()))
            return new Pair<>(this.getValue(), this.getData());
        else
            if(value.compareTo(this.getValue()) > 0)
                return this.right.find(value);
            else
                return this.left.find(value);
    }

    public List<Pair<V, D>> getAllData(){
        List<Pair<V, D>> l = new ArrayList<>();
        this._getAllData(l);
        return l;
    }

    private void _getAllData(List<Pair<V, D>> l){
        if(this.left != null)
            this.left._getAllData(l);
        l.add(new Pair(value, data));
        if(this.right != null)
            this.right._getAllData(l);
    }

    public V getValue() {
        return this.value;
    }

    public D getData() {
        return this.data;
    }
}
