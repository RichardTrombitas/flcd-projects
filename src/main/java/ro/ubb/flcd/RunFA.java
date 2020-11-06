package ro.ubb.flcd;

import java.io.FileNotFoundException;

public class RunFA {
    public static void main(String[] args) throws FileNotFoundException {
        FiniteAutomaton fa = new FiniteAutomaton("input/fa/fa-identifier.in");
        FiniteAutomatonUI ui = new FiniteAutomatonUI(fa);
        ui.start();
    }
}
