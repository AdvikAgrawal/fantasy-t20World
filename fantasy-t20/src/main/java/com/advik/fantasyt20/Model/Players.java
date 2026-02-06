package com.advik.fantasyt20.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Players {
    @Id
    private long id;
    private String playerName;
    private String playerRole;
    @ManyToOne
    @JoinColumn(name = "playerTeamId")
    private Squads squad;

    public Players() {

    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPlayerRole() {
        return playerRole;
    }
    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }
}
