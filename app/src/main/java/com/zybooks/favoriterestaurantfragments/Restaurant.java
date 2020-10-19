package com.zybooks.favoriterestaurantfragments;


public class Restaurant {
    private int Id;
    private String Name;
    private String Link;

    public Restaurant(int id, String name, String link) {
        Id = id;
        Name = name;
        Link = link;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }
}

