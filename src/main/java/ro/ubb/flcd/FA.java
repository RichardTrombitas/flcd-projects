package ro.ubb.flcd;

import ro.ubb.flcd.data_structure.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FA {
    private final List<String> alphabet = new ArrayList<>();
    private final List<String> states = new ArrayList<>();
    private final List<Transition> transitions = new ArrayList<>();
    private String initialState;
    private final List<String> finalStates = new ArrayList<>();

    public FA(String path) throws FileNotFoundException {
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
                while (resultStateTokenizer.hasMoreTokens()) {
                    transitions.add(new Transition(state, alphabet.get(i), resultStateTokenizer.nextToken()));
                }
                i++;
            }
        }
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
