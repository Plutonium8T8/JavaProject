package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Camera", schema = "public", catalog = "JavaProject")
public class CameraEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_camin")
    private int idCamin;

    @ManyToOne
    @JoinColumn(name = "id_camin", referencedColumnName = "id")
    private CaminEntity referencedCamin;

    public CaminEntity getReferencedContinent() {
        return referencedCamin;
    }

    public void setReferencedContinent(CaminEntity referencedContinent) {
        this.referencedCamin = referencedContinent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraEntity that = (CameraEntity) o;

        if (id != that.id) return false;
        if (idCamin != that.idCamin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idCamin;
        return result;
    }
}
