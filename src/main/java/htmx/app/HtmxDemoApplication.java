package htmx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "htmx.app")
public class HtmxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmxDemoApplication.class, args);
	}

}
