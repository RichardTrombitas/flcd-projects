package ro.ubb.lab2;

public class Main {
    public static void main(String[] args) {
        SymbolTable st = new SymbolTable();
        st.add("abc");
        st.add("acb");
        st.add("acaaaaab");
        System.out.println(st.getElemString(283));
        System.out.println(st.getElemString(313));
    }
}
