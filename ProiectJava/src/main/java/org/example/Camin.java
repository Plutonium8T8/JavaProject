package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Camin {
    private String name;
    private int ID;
    private int locuri;

    private List<Student> listaStudenti = new ArrayList<Student>();
    public Camin(String name, int locuri) {
        this.name = name;
        this.locuri = locuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

}
