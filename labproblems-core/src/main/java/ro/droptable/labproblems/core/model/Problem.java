package ro.droptable.labproblems.core.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by stefana on 4/11/2017.
 */
public class Problem extends BaseEntity<Long> implements Serializable {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public Problem() {
    }

    public Problem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Problem(Long id, String title, String description) {
        this.setId(id);
        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) &&
                Objects.equals(this.title, ((Problem)o).title) &&
                Objects.equals(this.description, ((Problem)o).description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result +  title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
