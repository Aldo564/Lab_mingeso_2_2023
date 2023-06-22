package Mingeso_Aldo.Planilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlanillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillaApplication.class, args);
	}

}
