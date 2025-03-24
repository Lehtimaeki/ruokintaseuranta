package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.service.RaakaaineService;

@Controller
public class RaakaaineController {

@Autowired
private RaakaaineService raakaaineService;

// Näytä lomake uuden raaka-aineen lisäämiseksi
@GetMapping("/raakaaineet/lisaa")
public String naytaLisaaRaakaaineLomake(Model model) {
    model.addAttribute("raakaaine", new Raakaaine());
    return "raakaaineetLista";
}

// Lisää uusi Raakaaine
@PostMapping("/raakaaineet/lisaa")
public String uusiRaakaaine(@ModelAttribute Raakaaine raakaaine) {
    raakaaineService.tallennaRaakaaine(raakaaine);
    return "redirect:/raakaaineet";
}

// Näytä kaikki Raakaaineet
@GetMapping("/raakaaineet")
public String haeKaikkiRaakaaineet(Model model) {
    List<Raakaaine> raakaaineet = raakaaineService.haeKaikkiRaakaaineet();
    model.addAttribute("raakaaineet", raakaaineet);
    model.addAttribute("raakaaine", new Raakaaine()); // Add this line
    return "raakaaineetLista";
}

// Näytä lomake Raakaaineen muokkaamiseksi
@GetMapping("/raakaaineet/muokkaa/{id}")
public String naytaMuokkaaRaakaaineLomake(@PathVariable("id") Long raakaaine_id, Model model) {
    Optional<Raakaaine> raakaaine = raakaaineService.haeRaakaaineId(raakaaine_id);
    if (raakaaine.isPresent()) {
        model.addAttribute("muokattavaRaakaine", raakaaine.get());
        return "raakaaineetMuokkaa";
    } else {
        return "redirect:/raakaaineet";
    }
}

// Päivitä Raakaaine
@PostMapping("/raakaaineet/muokkaa/{id}")
public String paivitaRaakaaine(@PathVariable("id") Long raakaaine_id, @ModelAttribute Raakaaine raakaaine) {
    raakaaineService.paivitaRaakaaine(raakaaine_id, raakaaine.getRaakaaineNimi());
    return "redirect:/raakaaineet";
}

// Poista raaka-aine
@PostMapping("/raakaaineet/poista/{id}")
public String poistaRaakaaine(@PathVariable("id") Long raakaaine_id) {
    raakaaineService.poistaRaakaaine(raakaaine_id);
    return "redirect:/raakaaineet";
}
}

