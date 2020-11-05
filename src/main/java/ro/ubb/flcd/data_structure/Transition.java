package ro.ubb.flcd.data_structure;

import lombok.Data;

@Data
public class Transition {
    private String state;
    private String symbol;
    private String resultState;

    public Transition(String state, String symbol, String resultState) {
        this.state = state;
        this.symbol = symbol;
        this.resultState = resultState;
    }

    @Override
    public String toString() {
        return "δ(" + state + ", " + symbol + ") = " + resultState;
    }
}
