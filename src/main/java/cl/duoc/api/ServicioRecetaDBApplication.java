package cl.duoc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicioRecetaDBApplication {

	public static void main(String[] args) {
		System.getProperties().put("server.port", 8081);
		SpringApplication.run(ServicioRecetaDBApplication.class, args);
	}

}
