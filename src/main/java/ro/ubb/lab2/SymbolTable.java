package ro.ubb.lab2;

public class SymbolTable {
    //The hash table in which the symbols are stored.
    //The size of the hash table, 4987, was chosen because it is big enough
    //to store most values and it is a prime number, which helps reduce collisions
    //(since the division method was used for the hash function).
    private final HashTable ht = new HashTable(4987);

    // Adds a symbol into the table.
    // input: symbol - String
    // output: -
    public void add(String symbol){
        ht.add(symbol);
    }

    // Verifies if a given symbol exists in the table.
    // input: symbol - String
    // output: true/false
    public boolean exists(String symbol){
        return ht.exists(symbol);
    }

    // Removes a symbol from the table
    // input: symbol - String
    // output: -
    public void remove(String symbol){
        ht.remove(symbol);
    }
}
