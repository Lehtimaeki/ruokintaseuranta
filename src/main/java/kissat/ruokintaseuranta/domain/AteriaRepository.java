package kissat.ruokintaseuranta.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="ateriat")
public interface AteriaRepository extends CrudRepository<Ateria, Long> {

}