package kissat.ruokintaseuranta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.domain.RaakaaineRepository;

@SpringBootApplication
public class RuokintaseurantaApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(RuokintaseurantaApplication.class);
	
	@Autowired
	private RaakaaineRepository rainerepository;

	public static void main(String[] args) {
		SpringApplication.run(RuokintaseurantaApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        rainerepository.save(new Raakaaine("Kana"));
		rainerepository.save(new Raakaaine("Nauta"));
		rainerepository.save(new Raakaaine("Turska"));

		for (Raakaaine raakaaine : rainerepository.findAll()) {
            logger.info(raakaaine.getRaakaaineNimi());

        }
    }

}
