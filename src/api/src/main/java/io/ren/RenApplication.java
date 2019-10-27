package io.ren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RenApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenApplication.class, args);
	}

}
