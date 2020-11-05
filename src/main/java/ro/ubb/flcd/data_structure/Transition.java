package ro.ubb.flcd.data_structure;

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
        return "Transition{" +
                "state='" + state + '\'' +
                ", symbol='" + symbol + '\'' +
                ", resultState='" + resultState + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }
}
