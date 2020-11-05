package ro.ubb.flcd.data_structure;

public class PIFEntry {
    private final String token;
    private final int stPos;

    public PIFEntry(String token, int stPos) {
        this.token = token;
        this.stPos = stPos;
    }

    public String getToken() {
        return token;
    }

    public int getStPos() {
        return stPos;
    }

    @Override
    public String toString() {
        return "\n(token= \"" + token + "\"" + ", stPos= " + stPos + ")";
    }
}
