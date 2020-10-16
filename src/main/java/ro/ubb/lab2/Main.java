package ro.ubb.lab2;

public class Main {
    public static void main(String[] args) {
        SymbolTable st = new SymbolTable();
        st.add("abc");
        st.add("abc");
        st.add("acb");
        st.add("acaaaaab");
        System.out.println(st.exists("abcf"));
        System.out.println(st.exists("abc"));
        st.remove("abc");
        System.out.println(st.exists("abc"));
    }
}
