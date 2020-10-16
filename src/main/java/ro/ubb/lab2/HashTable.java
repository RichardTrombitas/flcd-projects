package ro.ubb.lab2;

public class HashTable {
    //Array of singly linked lists, in which the elements are going to be stored.
    private final SinglyLinkedList[] table;

    //The size of the table array.
    private final int size;

    //Constructs a hash table with a given size.
    //input: size - int
    public HashTable(int size) {
        this.size = size;
        table = new SinglyLinkedList[size];
    }

    //Verifies if a given element exists in the hash table.
    //input: str - String
    //output: true/false
    public boolean exists(String str) {
        SinglyLinkedList sll = table[hash(str)];
        if(sll == null) {
            return false;
        }
        return table[hash(str)].exists(str);
    }

    //Adds a given element to the hash table.
    //input: str - String
    //output: -
    public void add(String str) {
        if(exists(str)) return;
        int pos = hash(str);
        if(table[pos] == null) {
            table[pos] = new SinglyLinkedList();
        }
        table[pos].addToHead(str);
    }

    //Removes a given element from the hash table
    //input: str - String
    //output: -
    public void remove(String str){
        int pos = hash(str);
        if(table[pos] == null) return;
        table[pos].remove(str);
    }

    //Computes the hash code of a given string, as a sum of the ASCII
    //codes of its characters.
    //input: str - String
    //output: hashCode - int
    private int hashCode(String str) {
        int hashCode = 0;
        for (int i = 0; i < str.length(); i++) {
            hashCode += str.charAt(i);
        }
        return hashCode;
    }

    //The hash function, i.e. the function which maps elements to their corresponding
    //position in the hash table. The division method is used.
    //input: str - String
    //output: int
    private int hash(String str) {
        return size % hashCode(str);
    }
}
