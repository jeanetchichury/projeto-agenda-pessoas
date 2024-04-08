package com.agenda.pessoas;

import com.agenda.pessoas.migration.FlywayMigrate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PessoasApplication {

	public static void main(String[] args) {
		new PessoasApplication().applyMigrations();
		SpringApplication.run(PessoasApplication.class, args);
	}

	public void applyMigrations() {
		FlywayMigrate flywayMigrate = new FlywayMigrate();
		flywayMigrate.migrate();
	}

}
