package com.miage.altea.game_ui.pokemonTypes;

import java.util.List;

public class Trainer {

    private String name;
    private String password;
    private List<Pokemon> team;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }
    public List<Pokemon> getTeam(){
        return this.team;
    }

    public Trainer() {}
    public Trainer(String name) {
        this.name = name;
    }
}
