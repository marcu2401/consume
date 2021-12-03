package hu.consumeSoldierAPI.domain;
public class Category {
    private int id;
    private String name;
    private int agelimit;

    public Category() {
    }

    public Category(String name, int agelimit) {
        this.name = name;
        this.agelimit = agelimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(int agelimit) {
        this.agelimit = agelimit;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", agelimit=" + agelimit +
                '}';
    }
}
