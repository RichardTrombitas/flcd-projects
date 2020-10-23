package ro.ubb.lab3;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<PIFEntry> entries = new ArrayList<>();

    public void add(String token, int stPos){
        entries.add(new PIFEntry(token, stPos));
    }

    public Integer getStPos(String token){
        for (PIFEntry entry: entries) {
            if(entry.getToken().equals(token))
                return entry.getStPos();
        }
        return null;
    }

    @Override
    public String toString() {
        return "PIF entries: \n" + entries.toString();
    }
}
