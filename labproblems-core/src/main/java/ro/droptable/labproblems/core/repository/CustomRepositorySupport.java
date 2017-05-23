package ro.droptable.labproblems.core.repository;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;
import ro.droptable.labproblems.core.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by vlad on 23.05.2017.
 */
@Getter
@Setter
public abstract class CustomRepositorySupport<T extends BaseEntity<ID>, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;
}
