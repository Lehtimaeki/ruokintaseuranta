package kissat.ruokintaseuranta.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="ruokinnat")
public interface RuokintaRepository extends CrudRepository<Ruokinta, Long> {
    
    @Query("SELECT r FROM Ruokinta r ORDER BY r.ruokintaAika DESC")
    List<Ruokinta> ruokinnatUusinEnsin();

}

