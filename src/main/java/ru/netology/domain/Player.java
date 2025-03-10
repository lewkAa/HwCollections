package ru.netology.domain;

public class Player {

    private String name;
    private int id;
    private int strength;

    public Player(String name, int id, int strength) {
        this.name = name;
        this.id = id;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", strength=" + strength +
                '}';
    }
}
