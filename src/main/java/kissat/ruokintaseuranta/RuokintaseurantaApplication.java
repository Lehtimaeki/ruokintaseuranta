package kissat.ruokintaseuranta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class RuokintaseurantaApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RuokintaseurantaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RuokintaseurantaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Voit lisätä muita käynnistyslogiikoita tähän, jos tarpeen
        logger.info("Ruokintaseuranta-sovellus käynnistetty.");
    }
}
