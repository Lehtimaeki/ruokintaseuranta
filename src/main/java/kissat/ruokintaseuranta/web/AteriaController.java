package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.List;

import kissat.ruokintaseuranta.domain.Ateria;
import kissat.ruokintaseuranta.service.AteriaService;

@Controller
public class AteriaController {

@Autowired
private AteriaService ateriaService;

@GetMapping("/ateriat")
public ResponseEntity<List<Ateria>> haeKaikkiAteriat() {
    List<Ateria> ateriat = ateriaService.haeKaikkiAteriat();
    return ResponseEntity.ok(ateriat);
}

// Lisää uusi ateria
@PostMapping("/ateriat")
public ResponseEntity<Ateria> uusiAteria(@RequestBody Ateria uusiAteria) {
    Ateria tallennettuAteria = ateriaService.uusiAteria(uusiAteria);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuAteria);
}

// Hae yksittäinen ateria ID:n perusteella
@GetMapping("/ateriat/{id}")
public ResponseEntity<Ateria> haeAteriaId(@PathVariable("id") Long ateriaId) {
    Optional<Ateria> ateria = ateriaService.haeAteriaId(ateriaId);
    return ateria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Päivitä ateria
@PutMapping("/ateriat/{id}")
public ResponseEntity<Ateria> paivitaAteria(@PathVariable("id") Long ateriaId, @RequestBody Ateria ateriaTiedot) {
    Optional<Ateria> ateria = ateriaService.paivitaAteria(ateriaId, ateriaTiedot);
    return ateria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Poista ateria
@DeleteMapping("/ateriat/{id}")
public ResponseEntity<Void> poistaAteria(@PathVariable("id") Long ateriaId) {
    if (ateriaService.poistaAteria(ateriaId)) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

}