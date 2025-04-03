package kissat.ruokintaseuranta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import kissat.ruokintaseuranta.domain.RaakaaineRepository;

import kissat.ruokintaseuranta.domain.Raakaaine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

@Service
public class RaakaaineService {
    @Autowired
    private RaakaaineRepository raakaaineRepository;

    public List<Raakaaine> haeKaikkiRaakaaineet() {
        List<Raakaaine> raakaaineet = StreamSupport
                .stream(raakaaineRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return raakaaineet.stream()
                .collect(Collectors.toList());
    }

    public Raakaaine tallennaRaakaaine(Raakaaine raakaaine) {
        return raakaaineRepository.save(raakaaine);
    }

    public Optional<Raakaaine> haeRaakaaineId(Long raakaaineId) {
        return raakaaineRepository.findById(raakaaineId);
    }

    public Optional<Raakaaine> paivitaRaakaaine(Long raakaaineId, String raakaaineNimi) {
        return raakaaineRepository.findById(raakaaineId).map(raakaaine -> {
            raakaaine.setRaakaaineNimi(raakaaineNimi);
            return raakaaineRepository.save(raakaaine);
        });
    }

    public boolean poistaRaakaaine(Long raakaaineId) {
        if (raakaaineRepository.existsById(raakaaineId)) {
            raakaaineRepository.deleteById(raakaaineId);
            return true;
        } else {
            return false;
        }
    }

}