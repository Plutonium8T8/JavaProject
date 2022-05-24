package org.example;

public class Student {
    private String nume;
    private String prenume;
    private String nationalitate;
    private String sex;
    private float media;
    private int id;
    private int idCamin;

    public Student(String nume, String prenume, String nationalitate, String sex, float media) {
        this.nume = nume;
        this.prenume = prenume;
        this.nationalitate = nationalitate;
        this.sex = sex;
        this.media = media;
    }
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCamin() {
        return idCamin;
    }

    public void setIdCamin(int idCamin) {
        this.idCamin = idCamin;
    }

}
