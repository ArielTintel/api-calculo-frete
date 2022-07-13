package br.com.arieltintel.calculofrete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class CalculoFreteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculoFreteApplication.class, args);
	}

}
