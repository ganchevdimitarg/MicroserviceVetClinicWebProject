package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.data.repository.AuthorityRepository;
import d2g.vetclinicwebproject.data.repository.UserRepository;
import d2g.vetclinicwebproject.services.models.GuestServiceModel;
import d2g.vetclinicwebproject.services.services.GuestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
    public static final String MICROSERVICE_USER_URL = "http://localhost:8082/user/";

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackUserHome",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")
            })
    public GuestServiceModel getUserHome(String username) {
        ResponseEntity<GuestServiceModel> resp = restTemplate.exchange(MICROSERVICE_USER_URL + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<GuestServiceModel>() {
                });

        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : new GuestServiceModel();
    }

    @Override
    public void registerUser(GuestServiceModel user) {
        restTemplate.postForObject(MICROSERVICE_USER_URL + "register-user", user, GuestServiceModel.class);
    }

    @Override
    public void update(GuestServiceModel user) {
        restTemplate.postForObject(MICROSERVICE_USER_URL + "update-user-info", user, GuestServiceModel.class);
    }

    @Override
    public void deleteUser(String username) {
        restTemplate.postForObject(MICROSERVICE_USER_URL + "delete-user/" + username, null, GuestServiceModel.class);
        UserEntity user = getByUsername(username);
        user.getAuthorities().removeAll(user.getAuthorities());
        authorityRepository.deleteAuthorityEntityByUser(user);
        userRepository.deleteById(user.getId());
    }

    private UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    public GuestServiceModel getFallbackUserHome(String username){
        return new GuestServiceModel();
    }
}
