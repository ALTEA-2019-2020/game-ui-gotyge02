package com.miage.altea.game_ui.pokemonTypes;

import java.util.List;

public class Trainer {

    public String name;
    public String password;
    public List<Pokemon> team;

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
    public Trainer(String name,String password) {
        this.name = name;this.password = password;
    }
    public String toString() {
        return this.name;
    }
}
