package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.service.RuokaService;
import kissat.ruokintaseuranta.service.ValmistajaService;
import kissat.ruokintaseuranta.service.RaakaaineService;

@Controller
@RequestMapping("/ruoat")
public class RuokaController {

    @Autowired
    private RuokaService ruokaService;

    @Autowired
    private ValmistajaService valmistajaService;

    @Autowired
    private RaakaaineService raakaaineService;

    @GetMapping
    public String haeKaikkiRuoat(Model model) {
        List<Ruoka> ruoat = ruokaService.haeKaikkiRuoat();
        List<Valmistaja> valmistajat = valmistajaService.haeKaikkiValmistajat();
        List<Raakaaine> raakaaineet = raakaaineService.haeKaikkiRaakaaineet();
        model.addAttribute("ruoat", ruoat);
        model.addAttribute("valmistajat", valmistajat);
        model.addAttribute("raakaaineet", raakaaineet);
        model.addAttribute("ruoka", new Ruoka());
        return "ruoatLista";
    }

    @PostMapping("/lisaa")
    public String lisaaRuoka(@Valid @ModelAttribute Ruoka uusiRuoka, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Valmistaja> valmistajat = valmistajaService.haeKaikkiValmistajat();
            List<Raakaaine> raakaaineet = raakaaineService.haeKaikkiRaakaaineet();
            model.addAttribute("valmistajat", valmistajat);
            model.addAttribute("raakaaineet", raakaaineet);
            model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
            return "ruoatLista";
        }
        ruokaService.tallennaRuoka(uusiRuoka);
        return "redirect:/ruoat";
    }

    @GetMapping("/muokkaa/{id}")
    public String naytaMuokkausLomake(@PathVariable("id") Long ruokaId, Model model) {
        Optional<Ruoka> ruoka = ruokaService.haeRuokaId(ruokaId);
        if (ruoka.isPresent()) {
            model.addAttribute("muokattavaRuoka", ruoka.get());
            model.addAttribute("valmistajat", valmistajaService.haeKaikkiValmistajat());
            model.addAttribute("raakaaineet", raakaaineService.haeKaikkiRaakaaineet());
            return "ruoatMuokkaa";
        } else {
            return "redirect:/ruoat";
        }
    }

    @PostMapping("/muokkaa")
    public String paivitaRuoka(@Valid @ModelAttribute Ruoka muokattavaRuoka, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("valmistajat", valmistajaService.haeKaikkiValmistajat());
            model.addAttribute("raakaaineet", raakaaineService.haeKaikkiRaakaaineet());
            return "ruoatMuokkaa";
        }
        ruokaService.tallennaRuoka(muokattavaRuoka);
        return "redirect:/ruoat";
    }

    @GetMapping("/poista/{id}")
    public String poistaRuoka(@PathVariable("id") Long ruokaId) {
        ruokaService.poistaRuoka(ruokaId);
        return "redirect:/ruoat";
    }
}
