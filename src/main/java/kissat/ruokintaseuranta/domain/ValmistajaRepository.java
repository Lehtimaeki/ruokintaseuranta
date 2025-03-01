package kissat.ruokintaseuranta.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="valmistajat")
public interface ValmistajaRepository extends CrudRepository<Valmistaja, Long> {

}
