package kissat.ruokintaseuranta.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="ruokinnat")
public interface RuokintaRepository extends CrudRepository<Ruokinta, Long> {

}

