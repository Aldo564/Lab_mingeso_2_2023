package Mingeso_Aldo.Proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProveedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProveedorApplication.class, args);
	}

}
