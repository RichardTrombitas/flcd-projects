package ro.ubb.flcd;

import ro.ubb.flcd.data_structure.HashTable;
import ro.ubb.flcd.data_structure.SinglyLinkedList;

public class SymbolTable {
    //The hash table in which the symbols are stored.
    //The size of the hash table, 4987, was chosen because it is big enough
    //to store most values and it is a prime number, which helps reduce collisions
    //(since the division method was used for the hash function).
    private final HashTable ht = new HashTable(4987);

    // Adds a symbol into the table. It is not added if it already exists.
    // input: symbol - String
    // output: an int representing the position of the symbol in the hash table
    public int add(String symbol) {
        return ht.add(symbol);
    }

    // Verifies if a given symbol exists in the table.
    // input: symbol - String
    // output: true/false
    public boolean exists(String symbol) {
        return ht.exists(symbol);
    }

    // Removes a symbol from the table
    // input: symbol - String
    // output: -
    public void remove(String symbol) {
        ht.remove(symbol);
    }

    // Returns a string representation of the linked list from a given position
    // in the symbol table.
    // input: pos - int
    // output: String
    public String getElemString(int pos) {
        return "pos " + pos + ": " + ht.getFromPos(pos).toString();
    }

    public String toString() {
        StringBuilder str = new StringBuilder("""
                The symbol table is implemented using a hash table. For conflict resolution, linked lists
                are used. Each position of the hash table corresponds to a linked list.

                Symbol table entries:
                """);
        for (int i = 0; i < ht.getSize(); i++) {
            SinglyLinkedList elems = ht.getFromPos(i);
            if (elems == null || elems.getHead() == null) {
                continue;
            }
            str.append(getElemString(i)).append("\n");
        }
        return str.toString();
    }
}
