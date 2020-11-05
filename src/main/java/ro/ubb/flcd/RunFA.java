package ro.ubb.flcd;

import java.io.FileNotFoundException;

public class RunFA {
    public static void main(String[] args) throws FileNotFoundException {
        FA fa = new FA("input/fa/fa.in");
        System.out.println(fa.toString());
    }
}
