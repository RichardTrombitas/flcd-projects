package ro.ubb.lab2;

public class SinglyLinkedList {
    //The first node of the linked list, denoted as the head.
    private SLLNode head;

    //Returns the head of the linked list.
    //input: -
    //output: head - SLLNode
    public SLLNode getHead(){
        return head;
    }

    //Adds a given element to the first position of the linked list.
    //input: value - String
    //output: -
    public void addToHead(String value){
        head = new SLLNode(value, head);
    }

    //Removes a given element from the linked list.
    //input: value - String
    //output: -
    public void remove(String value){
        if(head == null) return;
        SLLNode prev = null;
        SLLNode current = head;
        while(!current.getValue().equals(value)){
            prev = current;
            current = current.next();
            if(current == null) return;
        }

        if(prev == null){
            head = head.next();
            return;
        }
        prev.setNext(current.next());
    }

    //Verifies if a given element exists in the linked list.
    //input: value - String
    //output: true/false
    public boolean exists(String value){
        SLLIterator it = this.iterator();
        while(it.isValid()){
            if(it.getCurrent().equals(value)) return true;
            it.next();
        }
        return false;
    }

    //Returns an iterator over the linked list.
    //input: -
    //output: SLLIterator
    public SLLIterator iterator(){
        return new SLLIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        SLLIterator it = this.iterator();
        while(it.isValid()){
            str.append(it.getCurrent()).append(" ");
            it.next();
        }
        return str.toString();
    }
}
