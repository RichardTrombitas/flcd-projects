package ro.ubb.flcd;

import ro.ubb.flcd.exception.LexicalErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TokenScanner {
    private final List<String> reservedWords = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();
    private final List<String> separators = new ArrayList<>();
    private final String delimiterList;
    private StringTokenizer tokenizer;
    private int lineNr = 0;
    private String line;
    private String nextToken = "";
    private final Scanner scanner;

    public TokenScanner(File sourceCode, File tokensFile) throws FileNotFoundException {
        updateTokenLists(tokensFile);
        delimiterList = operators.stream().reduce("", (String a, String b) -> a + b) +
                separators.stream().reduce("", (String a, String b) -> a + b) + " \n'\"";
        scanner = new Scanner(sourceCode);
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lineNr++;
            tokenizer = new StringTokenizer(line, delimiterList, true);
        }
    }

    private void updateTokenLists(File tokensFile) throws FileNotFoundException {
        Scanner s = new Scanner(tokensFile);
        while (s.hasNext()) {
            String token = s.next();
            char codePrefix = s.next().charAt(0);
            if (codePrefix == '2') {
                reservedWords.add(token);
            } else if (codePrefix == '3') {
                operators.add(token);
            } else if (codePrefix == '4') {
                separators.add(token);
            }
        }
    }

    public void scan(PIF pif, SymbolTable st) throws LexicalErrorException {
        String token = detectNext();
        while (token != null) {
//            System.out.println(">" + token + "<");
            if (isReservedWord(token) || isOperator(token) || isSeparator(token)) {
                pif.add(token, -1);
            } else if (isIdentifier(token) || isConstant(token)) {
                int idx = pif.getStPos(token) != null ? pif.getStPos(token) : st.add(token);
                pif.add(isIdentifier(token) ? "id" : "const", idx);
            } else {
                throw new LexicalErrorException("Invalid token on line " + lineNr + ": " + token + "!");
            }
            token = detectNext();
        }
    }

    public boolean isReservedWord(String token) {
        return reservedWords.contains(token);
    }

    public boolean isOperator(String token) {
        return operators.contains(token);
    }

    public boolean isSeparator(String token) {
        return separators.contains(token);
    }

    public boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z][0-9_a-zA-Z]*");
    }

    public boolean isConstant(String token) {
        return token.matches("\"[a-zA-Z0-9]*\"") ||
                token.matches("'[a-zA-Z0-9]?'") ||
                token.matches("0") ||
                token.matches("[+-]?[1-9]+[0-9]*");
    }

    public String detectNext() throws LexicalErrorException {
        if (!nextToken.matches("['\"]") && !nextToken.equals("") &&
                !nextToken.equals(" ") && !nextToken.equals("\n")) {
            String ret = nextToken;
            nextToken = "";
            return ret;
        }
        if (!tokenizer.hasMoreTokens() && scanner.hasNextLine()) {
            line = scanner.nextLine();
            lineNr++;
            tokenizer = new StringTokenizer(line, delimiterList, true);
        }
        while (tokenizer.hasMoreTokens()) {
            StringBuilder token;
            if (nextToken.matches("['\"+-]")) {
                token = new StringBuilder(nextToken);
                nextToken = "";
            } else {
                token = new StringBuilder(tokenizer.nextToken());
            }
            if (token.toString().equals(" ") || token.toString().equals("\n")) continue;
            if (token.toString().matches("[+-]") && tokenizer.hasMoreTokens()) {
                nextToken = tokenizer.nextToken();
                if (nextToken.equals("0")) {
                    token.append(nextToken);
                    throw new LexicalErrorException("Invalid token on line " + lineNr + ": " + token.toString());
                }
                return token.toString();
            }
            if (token.toString().matches("[=<>]") && tokenizer.hasMoreTokens()) {
                nextToken = tokenizer.nextToken();
                if (nextToken.matches("[=<>]")) {
                    token.append(nextToken);
                    nextToken = "";
                }
                return token.toString();
            } else if (token.toString().equals("\"")) {
                while (tokenizer.hasMoreTokens()) {
                    nextToken = tokenizer.nextToken();
                    if (nextToken.equals("\"")) {
                        token.append(nextToken);
                        nextToken = "";
                        return token.toString();
                    }
                    token.append(nextToken);
                    nextToken = "";
                }
                throw new LexicalErrorException("Unclosed \" symbol on line " + lineNr + ": " + token + "!");
            } else if (token.toString().equals("'")) {
                if (tokenizer.hasMoreTokens()) {
                    nextToken = tokenizer.nextToken();
                    if (nextToken.equals("'")) {
                        token.append(nextToken);
                        nextToken = "";
                        return token.toString();
                    }
                    if (nextToken.length() > 1)
                        throw new LexicalErrorException("More than one character found after ' symbol on line " + lineNr + ": " + token + nextToken + "!");
                    token.append(nextToken);
                    if (tokenizer.hasMoreTokens()) {
                        nextToken = tokenizer.nextToken();
                        if (!nextToken.equals("'"))
                            throw new LexicalErrorException("More than one character found after ' symbol on line " + lineNr + ": " + token + nextToken + "!");
                        token.append(nextToken);
                        nextToken = "";
                        return token.toString();
                    }
                }
                throw new LexicalErrorException("Unclosed ' symbol on line " + lineNr + ": " + token + "!");
            } else {
                return token.toString();
            }
        }
        return null;
    }
}
