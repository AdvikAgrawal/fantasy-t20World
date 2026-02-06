package com.advik.fantasyt20.Model;

public class PlayerStat {

    private Long playerId;

    private int runs;
    private int wickets;
    private int catches;

    public PlayerStat(Long playerId, int runs, int wickets, int catches) {
        this.playerId = playerId;
        this.runs = runs;
        this.wickets = wickets;
        this.catches = catches;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public int getCatches() {
        return catches;
    }
}
