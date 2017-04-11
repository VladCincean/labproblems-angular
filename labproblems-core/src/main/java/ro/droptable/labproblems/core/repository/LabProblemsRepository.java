package ro.droptable.labproblems.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.labproblems.core.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by vlad on 11.04.2017.
 */
@NoRepositoryBean
@Transactional
public interface LabProblemsRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
