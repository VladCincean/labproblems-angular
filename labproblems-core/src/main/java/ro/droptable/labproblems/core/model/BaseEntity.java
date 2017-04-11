package ro.droptable.labproblems.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vlad on 11.04.2017.
 *
 * A generic class for representing a 'real-world' entity that can be identified by an object of type {@code ID}.
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @TableGenerator(name = "TABLE_GENERATOR", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
    @Column(unique = true, nullable = false)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
