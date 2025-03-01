package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

import kissat.ruokintaseuranta.domain.Ateria;
import kissat.ruokintaseuranta.domain.AteriaRepository;


@RestController
public class AteriaController {

@Autowired
private AteriaRepository ateriarepo;

//Lisää uusi Ateria
@PostMapping("/ateriat")
public ResponseEntity<Ateria> uusiAteria(@RequestBody Ateria uusiAteria) {
    Ateria tallennettuAteria = ateriarepo.save(uusiAteria);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuAteria);
}

//Hae kaikki Ateria
@GetMapping("/ateriat")
public List<Ateria> haeKaikkiAteriat() {
    Iterable<Ateria> ateriat = ateriarepo.findAll();
    return StreamSupport.stream(ateriat.spliterator(), 
    false).collect(Collectors.toList());
}

// Hae yksittäinen Ateria ID:n perusteella
@GetMapping("/ateriat/{id}")
public ResponseEntity<Ateria> haeAteriaId(@PathVariable("id") Long ateriaId) {
        Optional<Ateria> ateria = ateriarepo.findById(ateriaId);
        return ateria.map(ResponseEntity::ok).orElseGet(() -> 
        ResponseEntity.notFound().build());
}

// Päivitä Ateria
@PutMapping("/ateriat/{id}")
public ResponseEntity<Ateria> paivitaAteria(@PathVariable("id") Long ateriaId, @RequestBody Ateria ateriaTiedot) {
    Optional<Ateria> ateria = ateriarepo.findById(ateriaId);
    if (ateria.isPresent()) {
        Ateria paivitettyAteria = ateria.get();
        paivitettyAteria.setAteriaNimi(ateriaTiedot.getAteriaNimi());
       ateriarepo.save(paivitettyAteria);
        return ResponseEntity.ok(paivitettyAteria);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista Ateria
@DeleteMapping("/ateriat/{id}")
public ResponseEntity<Void> poistaAteria(@PathVariable("id") Long ateriaId) {
    if (ateriarepo.existsById(ateriaId)) {
        ateriarepo.deleteById(ateriaId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}


}