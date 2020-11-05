package ro.ubb.flcd.data_structure;

public class SLLIterator {
    //The singly linked list over which the iteration is made.
    private final SinglyLinkedList linkedList;

    //The current node the iterator points to.
    private SLLNode current;

    //Constructs an iterator over a given singly linked list.
    //input: linkedList - SinglyLinkedList
    public SLLIterator(SinglyLinkedList linkedList) {
        this.linkedList = linkedList;
        reset();
    }

    //Resets the iteration over a linked list, i.e. the current node becomes the head.
    public void reset(){
        current = linkedList.getHead();
    }

    //Returns the value stored in the current node of the linked list.
    //input: -
    //output: String
    public String getCurrent(){
        return current.getValue();
    }

    //Performs an iteration, i.e. the current node will point to the next node.
    public void next(){
        current = current.next();
    }

    //Verifies if the current element is valid, i.e. the current node is not null.
    //input: -
    //output: true/false
    public boolean isValid(){
        return !(current == null);
    }
}
