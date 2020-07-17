package d2g.vetclinicwebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VetClinicWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetClinicWebProjectApplication.class, args);
    }

}
