package com.example.KafkaStreams.tasks.task4;

public class User {
    private String name;
    private String company;
    private String position;
    private int experience;

    public User() {
    }

    public User(String name, String company, String position, int experience) {
        this.name = name;
        this.company = company;
        this.position = position;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", experience=" + experience +
                '}';
    }
}
