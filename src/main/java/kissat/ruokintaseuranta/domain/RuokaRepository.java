package kissat.ruokintaseuranta.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="ruoat")
public interface RuokaRepository extends CrudRepository<Ruoka, Long> {

}