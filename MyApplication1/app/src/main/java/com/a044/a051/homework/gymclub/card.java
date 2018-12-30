package com.a044.a051.homework.gymclub;

public class card {
    private String name,name1;
    private int imageid;

    public card(String name, String name1, int imageid) {
        this.name=name;
        this.name1=name1;
        this.imageid=imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }

    public String getNames() {return this.name1; }
}
