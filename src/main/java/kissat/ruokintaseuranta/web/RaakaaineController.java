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
import java.util.Optional;

import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.dto.RaakaaineDTO;
import kissat.ruokintaseuranta.service.RaakaaineService;


@RestController
public class RaakaaineController {

@Autowired
private RaakaaineService raakaaineService;

//Lisää uusi Raakaaine
@PostMapping("/raakaaineet")
public ResponseEntity<Raakaaine> uusiRaakaaine(@RequestBody Raakaaine uusiRaakaaine) {
    Raakaaine tallennettuRaakaaine = raakaaineService.tallennaRaakaaine(uusiRaakaaine);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRaakaaine);
}
//Hae kaikki Raakaaineet
@GetMapping("/raakaaineet")
public ResponseEntity<List<Raakaaine>> haeKaikkiRaakaaineet() {
    List<Raakaaine> raakaaineet = raakaaineService.haeKaikkiRaakaaineet();
    return ResponseEntity.ok(raakaaineet);
}

// Hae yksittäinen Raakaaine ID:n perusteella
@GetMapping("/raakaaineet/{id}")
public ResponseEntity<Raakaaine> haeRaakaaineId(@PathVariable("id") Long raakaaine_id) {
    Optional<Raakaaine> raakaaine = raakaaineService.haeRaakaaineId(raakaaine_id);
    return raakaaine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
// Päivitä Raakaaine
@PutMapping("/raakaaineet/{id}")
public ResponseEntity<Raakaaine> paivitaRaakaaine(@PathVariable("id") Long raakaaine_id, @RequestBody RaakaaineDTO raakaaineDTO) {
    Optional<Raakaaine> paivitettyRaakaaine = raakaaineService.paivitaRaakaaine(raakaaine_id, raakaaineDTO.getRaakaaineNimi());
    return paivitettyRaakaaine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
// Poista raaka-aine
@DeleteMapping("/raakaaineet/{id}")
public ResponseEntity<Void> poistaRaakaaine(@PathVariable("id") Long raakaaine_id) {
    if (raakaaineService.poistaRaakaaine(raakaaine_id)) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
}

