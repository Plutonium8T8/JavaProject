package Entity;

import javax.persistence.*;

@Entity
@Table(name = "camera", schema = "public", catalog = "JavaProject")
public class CameraEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "id_camin",insertable = false, updatable = false, nullable = false)
    private int idCamin;

    @ManyToOne
    @JoinColumn(name = "id_camin", referencedColumnName = "id")
    private CaminEntity referencedCamin;

    @Basic
    @Column(name = "capacitate")
    private int capacitate;

    //returneaza referinta caminului de unde face parte camera
    public CaminEntity getReferencedCamin() {
        return referencedCamin;
    }

    //seteaza referinta caminului de unde face parte camera
    public void setReferencedCamin(CaminEntity referencedCamin) {
        this.referencedCamin = referencedCamin;
    }

    //returneaza IDul camerei
    public int getId() {
        return id;
    }

    //seteaza IDul camerei
    public void setId(int id) {
        this.id = id;
    }

    //returneaza IDul caminului de unde face parte camera
    public int getIdCamin() {
        return idCamin;
    }

    //seteaza IDul caminului de unde face parte camera
    public void setIdCamin(int idCamin) {
        this.idCamin = idCamin;
    }

    //returneaza capacitatea camerei
    public int getCapacitate() {
        return capacitate;
    }

    //seteaza capacitatea camerei
    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraEntity that = (CameraEntity) o;

        if (id != that.id) return false;
        if (idCamin != that.idCamin) return false;
        if (capacitate != that.capacitate) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idCamin;
        result = 31 * result + capacitate;
        return result;
    }
}
