package co.com.neoris.banco.gestorcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class,SessionAutoConfiguration.class})
@EntityScan(basePackages = "co.com.neoris.banco", basePackageClasses = {GestorClienteApplication.class})
@EnableJpaRepositories("co.com.neoris.banco.comun.repository")
public class GestorClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorClienteApplication.class, args);
	}

}
