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

import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.domain.ValmistajaRepository;


@RestController
public class ValmistajaController {

@Autowired
private ValmistajaRepository valmistajarepo;

//Lisää uusi valmistaja
@PostMapping("/valmistajat")
public ResponseEntity<Valmistaja> uusiValmistaja(@RequestBody Valmistaja uusiValmistaja) {
    Valmistaja tallennettuValmistaja = valmistajarepo.save(uusiValmistaja);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuValmistaja);
}

//Hae kaikki valmistajat
@GetMapping("/valmistajat")
public List<Valmistaja> haeKaikkiValmistajat() {
    Iterable<Valmistaja> valmistajat = valmistajarepo.findAll();
    return StreamSupport.stream(valmistajat.spliterator(), 
    false).collect(Collectors.toList());
}

// Hae yksittäinen valmistaja ID:n perusteella
@GetMapping("/valmistajat/{id}")
public ResponseEntity<Valmistaja> haeValmistajaId(@PathVariable("id") Long valmistajaId) {
        Optional<Valmistaja> valmistaja = valmistajarepo.findById(valmistajaId);
        return valmistaja.map(ResponseEntity::ok).orElseGet(() -> 
        ResponseEntity.notFound().build());
}

// Päivitä valmistaja
@PutMapping("/valmistajat/{id}")
public ResponseEntity<Valmistaja> paivitaValmistaja(@PathVariable("id") Long valmistajaId, @RequestBody Valmistaja valmistajaTiedot) {
    Optional<Valmistaja> valmistaja = valmistajarepo.findById(valmistajaId);
    if (valmistaja.isPresent()) {
        Valmistaja paivitettyValmistaja = valmistaja.get();
        paivitettyValmistaja.setValmistajaNimi(valmistajaTiedot.getValmistajaNimi());
        valmistajarepo.save(paivitettyValmistaja);
        return ResponseEntity.ok(paivitettyValmistaja);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista valmistaja
@DeleteMapping("/valmistajat/{id}")
public ResponseEntity<Void> poistaValmistaja(@PathVariable("id") Long valmistajaId) {
    if (valmistajarepo.existsById(valmistajaId)) {
        valmistajarepo.deleteById(valmistajaId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}


}
