package cl.binter.challenge.backendapadilla;

import cl.binter.challenge.backendapadilla.wordcounter.model.TextItem;
import cl.binter.challenge.backendapadilla.wordcounter.service.impl.TextServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class BackendApadillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApadillaApplication.class, args);
	}

}
