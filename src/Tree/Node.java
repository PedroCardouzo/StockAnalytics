package Tree;


public class Node<V extends Comparable<V>, D>{
    private V value;
    private D data;
    private Node<V, D> left;
    private Node<V, D> right;

    Node(V value){
        this(value, null);
    }

    Node(V value, D data){
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

    public V getValue() {
        return this.value;
    }

    public D getData() {
        return this.data;
    }
}
