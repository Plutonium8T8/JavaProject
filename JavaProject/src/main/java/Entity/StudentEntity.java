package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public", catalog = "JavaProject")
public class StudentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "id_camera",insertable = false, updatable = false, nullable = false)
    private Integer idCamera;
    @Basic
    @Column(name = "nume")
    private String nume;
    @Basic
    @Column(name = "prenume")
    private String prenume;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "nationalitate")
    private String nationalitate;
    @Basic
    @Column(name = "medie")
    private Float medie;
    @Basic
    @Column(name = "camera_pref")
    private Integer cameraPref;

    @ManyToOne
    @JoinColumn(name = "id_camera", referencedColumnName = "id")
    private CameraEntity referencedCamera;

    //returneaza referinta camerei
    public CameraEntity getReferencedCamera() {return referencedCamera;}

    //seteaza referinta unei camere
    public void setReferencedCamera(CameraEntity referencedCamera) {
        this.referencedCamera = referencedCamera;
    }

    //returneaza IDul studentului
    public int getId() {
        return id;
    }

    //seteaza un ID studentului
    public void setId(int id) {
        this.id = id;
    }

    //returneaza IDul camerei pe care o prefera studentul
    public int getCameraPref() {
        return cameraPref;
    }

    //seteaza IDul camerei preferata de student
    public void setCameraPref(int cameraPref) {
        this.cameraPref = cameraPref;
    }

    //returneaza IDul camerei studentului
    public int getIdCamera() {
        return idCamera;
    }

    //seteaza IDul camerei studentului
    public void setIdCamera(Integer idCamera) {
        this.idCamera = idCamera;
    }

    //returneaza numele studentului
    public String getNume() {
        return nume;
    }

    //seteaza numele studentului
    public void setNume(String nume) {
        this.nume = nume;
    }

    //returneaza prenumele studentului
    public String getPrenume() {
        return prenume;
    }

    //seteaza prenumele studentului
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    //returneaza sexul studentului
    public String getSex() {
        return sex;
    }

    //seteaza sexul studentului
    public void setSex(String sex) {
        this.sex = sex;
    }

    //returneaza nationalitatea studentului
    public String getNationalitate() {
        return nationalitate;
    }

    //seteaza nationalitatea studentului
    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    //returneaza media studentului
    public Float getMedie() {
        return medie;
    }

    //seteaza media studentului
    public void setMedie(Float medie) {
        this.medie = medie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity student = (StudentEntity) o;

        if (id != student.id) return false;
        if (!Objects.equals(idCamera, student.idCamera)) return false;
        if (nume != null ? !nume.equals(student.nume) : student.nume != null) return false;
        if (prenume != null ? !prenume.equals(student.prenume) : student.prenume != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        if (nationalitate != null ? !nationalitate.equals(student.nationalitate) : student.nationalitate != null) return false;
        if (medie != null ? !medie.equals(student.medie) : student.medie != null) return false;
        if (cameraPref != null ? !cameraPref.equals(student.cameraPref) : student.cameraPref != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idCamera != null ? nume.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + (prenume != null ? prenume.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nationalitate != null ? nationalitate.hashCode() : 0);
        result = 31 * result + (medie != null ? medie.hashCode() : 0);
        result = 31 * result + (cameraPref != null ? cameraPref.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  nume +
                "," + prenume  +
                "," + sex +
                "," + medie +
                "," + idCamera +
                "," + cameraPref+
                "," + nationalitate;
    }
}
