package ro.ubb.flcd;

import lombok.Getter;
import ro.ubb.flcd.data_structure.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

@Getter
public class FiniteAutomaton {
    private final String path;
    private boolean isDeterministic = true;
    private final List<String> alphabet = new ArrayList<>();
    private final List<String> states = new ArrayList<>();
    private final List<Transition> transitions = new ArrayList<>();
    private String initialState;
    private final List<String> finalStates = new ArrayList<>();

    public FiniteAutomaton(String path) throws FileNotFoundException {
        this.path = path;
        File faFile = new File(path);
        Scanner scanner = new Scanner(faFile);
        StringTokenizer tokenizer;

        if (scanner.hasNextLine()) {
            tokenizer = new StringTokenizer(scanner.nextLine(), " ");
            while (tokenizer.hasMoreTokens()) {
                alphabet.add(tokenizer.nextToken());
            }
        }

        while (scanner.hasNextLine()) {
            tokenizer = new StringTokenizer(scanner.nextLine(), " ");
            if (!tokenizer.hasMoreTokens()) continue;
            String state = tokenizer.nextToken();
            if (state.equals("->") && tokenizer.hasMoreTokens()) {
                state = tokenizer.nextToken();
                if (state.equals("*") && tokenizer.hasMoreTokens()) {
                    state = tokenizer.nextToken();
                    finalStates.add(state);
                }
                initialState = state;
            } else if (state.equals("*") && tokenizer.hasMoreTokens()) {
                state = tokenizer.nextToken();
                finalStates.add(state);
            }
            states.add(state);
            int i = 0;
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (token.equals("{}")) {
                    transitions.add(new Transition(state, alphabet.get(i), token));
                    i++;
                    continue;
                }
                StringTokenizer resultStateTokenizer = new StringTokenizer(token, "{},");
                int elemCount = 0;
                while (resultStateTokenizer.hasMoreTokens()) {
                    transitions.add(new Transition(state, alphabet.get(i), resultStateTokenizer.nextToken()));
                    elemCount++;
                }
                    if(elemCount > 1) isDeterministic = false;
                i++;
            }
        }
    }

    public boolean isAccepted(String sequence) {
        if(!isDeterministic) throw new RuntimeException("Can't verify sequences if the FA is not deterministic!");
        String state = initialState;
        char[] charSequence = sequence.toCharArray();
        for (int i = 0; i < charSequence.length; i++) {
            if(!alphabet.contains(String.valueOf(charSequence[i]))) return false;
            for (Transition t : transitions) {
                if (t.getState().equals(state) && t.getSymbol().equals(String.valueOf(charSequence[i]))) {
                    state = t.getResultState();
                    if (finalStates.contains(state) && i == charSequence.length - 1) return true;
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("FA{" +
                "alphabet=" + alphabet +
                ", states=" + states +
                ", initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates + ",\n\ttransitions=\n\t\t[");
        res.append(transitions.get(0).toString()).append(",\n");
        for (int i = 1; i < transitions.size(); i++) {
            res.append("\t\t ").append(transitions.get(i).toString()).append(",\n");
        }
        res.replace(res.length() - 2, res.length() - 1, "]\n}");
        return res.toString();
    }
}
