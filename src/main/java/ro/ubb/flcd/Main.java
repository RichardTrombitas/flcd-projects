package ro.ubb.flcd;

import ro.ubb.flcd.exception.LexicalErrorException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File tokensFile = new File("input/token.in");
        File p1 = new File("input/p1.txt");
        File p2 = new File("input/p2.txt");
        File p3 = new File("input/p3.txt");
        File p1err = new File("input/p1err.txt");

        SymbolTable st = new SymbolTable();
        PIF pif = new PIF();

        try {
            TokenScanner scanner = new TokenScanner(p1, tokensFile);
            scanner.scan(pif, st);
            FileWriter pifWriter = new FileWriter("output/PIF.out");
            FileWriter stWriter = new FileWriter("output/ST.out");
            pifWriter.write(pif.toString());
            stWriter.write(st.toString());
            pifWriter.close();
            stWriter.close();
            System.out.println("The source code is lexically correct.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexicalErrorException e){
            System.out.println("Lexical error! " + e.getMessage());
        }
    }
}
