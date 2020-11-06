package ro.ubb.flcd;

import ro.ubb.flcd.exception.LexicalErrorException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File tokensFile = new File("input/programs/token.in");
        File p1 = new File("input/programs/p1.txt");
        File p2 = new File("input/programs/p2.txt");
        File p3 = new File("input/programs/p3.txt");
        File p1err = new File("input/programs/p1err.txt");

        SymbolTable st = new SymbolTable();
        PIF pif = new PIF();

        try {
            TokenScanner scanner = new TokenScanner(p1, tokensFile, "input/fa/fa-identifier.in", "input/fa/fa-integer.in");
            scanner.scan(pif, st);
            FileWriter pifWriter = new FileWriter("output/programs/PIF.out");
            FileWriter stWriter = new FileWriter("output/programs/ST.out");
            pifWriter.write(pif.toString());
            stWriter.write(st.toString());
            pifWriter.close();
            stWriter.close();
            System.out.println("The source code is lexically correct.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexicalErrorException e) {
            System.out.println("Lexical error! " + e.getMessage());
        }
    }
}
