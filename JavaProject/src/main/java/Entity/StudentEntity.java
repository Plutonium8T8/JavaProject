package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Student", schema = "public", catalog = "JavaProject")
public class StudentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nume")
    private String nume;
    @Basic
    @Column(name = "prenume")
    private String prenume;
    @Basic
    @Column(name = "id_camera")
    private Integer idCamera;
    @Basic
    @Column(name = "medie")
    private Float medie;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "nationalitate")
    private String nationalitate;

    @ManyToOne
    @JoinColumn(name = "id_camera", referencedColumnName = "id")
    private CameraEntity referencedRoom;

    public CameraEntity getReferencedContinent() {
        return referencedRoom;
    }

    public void setReferencedContinent(CameraEntity referencedContinent) {
        this.referencedRoom = referencedRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getIdCamera() {
        return idCamera;
    }

    public void setIdCamera(Integer idCamera) {
        this.idCamera = idCamera;
    }

    public Float getMedie() {
        return medie;
    }

    public void setMedie(Float medie) {
        this.medie = medie;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (id != that.id) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (prenume != null ? !prenume.equals(that.prenume) : that.prenume != null) return false;
        if (idCamera != null ? !idCamera.equals(that.idCamera) : that.idCamera != null) return false;
        if (medie != null ? !medie.equals(that.medie) : that.medie != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (nationalitate != null ? !nationalitate.equals(that.nationalitate) : that.nationalitate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        result = 31 * result + (idCamera != null ? idCamera.hashCode() : 0);
        result = 31 * result + (medie != null ? medie.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nationalitate != null ? nationalitate.hashCode() : 0);
        return result;
    }
}
