package eskere.HTMXDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "eskere.HTMXDemo")
public class HtmxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmxDemoApplication.class, args);
	}

}
