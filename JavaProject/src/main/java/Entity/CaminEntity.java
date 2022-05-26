package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Camin", schema = "public", catalog = "JavaProject")
public class CaminEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nume_camin")
    private String numeCamin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeCamin() {
        return numeCamin;
    }

    public void setNumeCamin(String numeCamin) {
        this.numeCamin = numeCamin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaminEntity that = (CaminEntity) o;

        if (id != that.id) return false;
        if (numeCamin != null ? !numeCamin.equals(that.numeCamin) : that.numeCamin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numeCamin != null ? numeCamin.hashCode() : 0);
        return result;
    }
}
