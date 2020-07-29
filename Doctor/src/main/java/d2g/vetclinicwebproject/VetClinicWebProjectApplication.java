package d2g.vetclinicwebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VetClinicWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetClinicWebProjectApplication.class, args);
    }

}
