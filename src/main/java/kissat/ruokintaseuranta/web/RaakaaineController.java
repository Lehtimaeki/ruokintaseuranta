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

import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.domain.RaakaaineRepository;


@RestController
public class RaakaaineController {

@Autowired
private RaakaaineRepository raakaainerepo;

//Lisää uusi Raakaaine
@PostMapping("/raakaaineet")
public ResponseEntity<Raakaaine> uusiRaakaaine(@RequestBody Raakaaine uusiRaakaaine) {
    Raakaaine tallennettuRaakaaine = raakaainerepo.save(uusiRaakaaine);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRaakaaine);
}

//Hae kaikki Raakaaineet
@GetMapping("/raakaaineet")
public List<Raakaaine> haeKaikkiRaakaaineet() {
    Iterable<Raakaaine> raakaaineet = raakaainerepo.findAll();
    return StreamSupport.stream(raakaaineet.spliterator(), 
    false).collect(Collectors.toList());
}

// Hae yksittäinen Raakaaine ID:n perusteella
@GetMapping("/raakaaineet/{id}")
public ResponseEntity<Raakaaine> haeRaakaaineId(@PathVariable("id") Long raakaaineId) {
        Optional<Raakaaine> raakaaine = raakaainerepo.findById(raakaaineId);
        return raakaaine.map(ResponseEntity::ok).orElseGet(() -> 
        ResponseEntity.notFound().build());
}

// Päivitä Raakaaine
@PutMapping("/raakaaineet/{id}")
public ResponseEntity<Raakaaine> paivitaRaakaaine(@PathVariable("id") Long raakaaineId, @RequestBody Raakaaine raakaaineTiedot) {
    Optional<Raakaaine> raakaaine = raakaainerepo.findById(raakaaineId);
    if (raakaaine.isPresent()) {
        Raakaaine paivitettyRaakaaine = raakaaine.get();
        paivitettyRaakaaine.setRaakaaineNimi(raakaaineTiedot.getRaakaaineNimi());
        return ResponseEntity.ok(paivitettyRaakaaine);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista valmistaja
@DeleteMapping("/raakaaineet/{id}")
public ResponseEntity<Void> poistaRaakaaine(@PathVariable("id") Long raakaaineId) {
    if (raakaainerepo.existsById(raakaaineId)) {
        raakaainerepo.deleteById(raakaaineId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}


}
