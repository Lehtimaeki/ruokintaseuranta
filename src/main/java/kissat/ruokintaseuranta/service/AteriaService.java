package kissat.ruokintaseuranta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import kissat.ruokintaseuranta.domain.Ateria;
import kissat.ruokintaseuranta.domain.AteriaRepository;

@Service
public class AteriaService {

@Autowired
private AteriaRepository ateriaRepo;

public List<Ateria> haeKaikkiAteriat() {
    Iterable<Ateria> ateriat = ateriaRepo.findAll();
    return StreamSupport.stream(ateriat.spliterator(), false).collect(Collectors.toList());
}

public Ateria uusiAteria(Ateria uusiAteria) {
    return ateriaRepo.save(uusiAteria);
}

public Optional<Ateria> haeAteriaId(Long ateriaId) {
    return ateriaRepo.findById(ateriaId);
}

public Optional<Ateria> paivitaAteria(Long ateriaId, Ateria ateriaTiedot) {
    Optional<Ateria> ateria = ateriaRepo.findById(ateriaId);
    if (ateria.isPresent()) {
        Ateria paivitettyAteria = ateria.get();
        paivitettyAteria.setAteriaNimi(ateriaTiedot.getAteriaNimi());
        return Optional.of(ateriaRepo.save(paivitettyAteria));
    } else {
        return Optional.empty();
    }
}

public boolean poistaAteria(Long ateriaId) {
    if (ateriaRepo.existsById(ateriaId)) {
        ateriaRepo.deleteById(ateriaId);
        return true;
    } else {
        return false;
    }
}

}
