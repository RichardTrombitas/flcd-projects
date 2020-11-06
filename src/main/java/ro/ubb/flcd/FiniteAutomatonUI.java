package ro.ubb.flcd;

import java.util.Scanner;

public class FiniteAutomatonUI {
    private final FiniteAutomaton fa;

    public FiniteAutomatonUI(FiniteAutomaton fa) {
        this.fa = fa;
    }

    private void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("\t1. See the set of states");
        System.out.println("\t2. See the alphabet");
        System.out.println("\t3. See transitions");
        System.out.println("\t4. See the set of final states");
        System.out.println("\t5. Check if the FA is deterministic");
        System.out.println("\t6. Check if a sequence is accepted");
        System.out.println("\t0. Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFinite automaton loaded from file: " + fa.getPath() + ".");
        printMenu();
        while (true) {
            switch (scanner.next()) {
                case "1" -> {
                    System.out.println("states: " + fa.getStates());
                    printMenu();
                }
                case "2" -> {
                    System.out.println("alphabet: " + fa.getAlphabet());
                    printMenu();
                }
                case "3" -> {
                    System.out.println("transitions:");
                    fa.getTransitions().forEach(System.out::println);
                    printMenu();
                }
                case "4" -> {
                    System.out.println("final states: " + fa.getFinalStates());
                    printMenu();
                }
                case "5" -> System.out.println(fa.isDeterministic() ? "The FA is deterministic." : "The FA is not deterministic.");
                case "6" -> {
                    if(!fa.isDeterministic()) {
                        System.out.println("Can't verify sequences if the FA is not deterministic!");
                        break;
                    }
                    System.out.print("Enter the sequence: ");
                    System.out.println(fa.isAccepted(scanner.next()) ? "The sequence is accepted." :
                            "The sequence is not accepted.");
                    printMenu();
                }
                case "0" -> {
                    System.exit(0);
                    printMenu();
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
}
