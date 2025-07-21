package personal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StackoverflowApp {

	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApp.class, args);
	}

}
