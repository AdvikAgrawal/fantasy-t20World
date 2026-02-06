package com.advik.fantasyt20.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserTeamPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private MyAppUser myAppUser;
    public MyAppUser getMyAppUser() {
        return myAppUser;
    }
    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Players player;
    private Integer slot;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Players getPlayer() {
        return player;
    }
    public void setPlayer(Players player) {
        this.player = player;
    }
    public Integer getSlot() {
        return slot;
    }
    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
