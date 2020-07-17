package d2g.vetclinicwebproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        //ако не върне отговор до 3сек, ще върне грешка
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//            clientHttpRequestFactory.setConnectTimeout(3000);
        return builder.build();
    }

    @Bean
    public PasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }
}
