package ro.ubb.lab3.data_structures;

public class SLLNode {
    //The information stored in the node, i.e. a string value.
    private String value;

    //A pointer towards another node.
    private SLLNode next;

    //Constructs a new node.
    //input: value - String
    //output: next - SLLNode
    public SLLNode(String value, SLLNode next) {
        this.value = value;
        this.next = next;
    }

    //Returns the value stored in the node.
    //input: -
    //output: value - String
    public String getValue() {
        return value;
    }

    //Sets the value stored inside the node.
    //input: value - String
    //output: -
    public void setValue(String value) {
        this.value = value;
    }

    //Returns the node pointed to by the current node.
    //input: -
    //output: next - SLLNode
    public SLLNode next() {
        return next;
    }

    //Sets the node pointed to by the current node.
    //input: next - SLLNode
    //output: -
    public void setNext(SLLNode next) {
        this.next = next;
    }
}
