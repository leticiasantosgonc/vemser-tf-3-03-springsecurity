package br.com.dbc.vemser.tf03spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = {
				@Server(url = "/leticiasantosgonc/vemser-tf-3-03-springsecurity", description = "Default Server URL")
		}
)
public class DbcursosTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbcursosTechApplication.class, args);
	}

}
