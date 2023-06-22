package Mingeso_Aldo.Pep2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Pep2Application {

	public static void main(String[] args) {
		SpringApplication.run(Pep2Application.class, args);
	}

}
