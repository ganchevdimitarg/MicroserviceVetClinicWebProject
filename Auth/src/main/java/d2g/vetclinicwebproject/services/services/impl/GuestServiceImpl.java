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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
    public static final String MICROSERVICE_USER_URL = "http://localhost:8082/user/";

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserHomePage",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1")
            },
            threadPoolKey = "userHomePage",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000")
            })
    @Override
    public GuestServiceModel getUserHome(String username) {
        return restTemplate.exchange(MICROSERVICE_USER_URL + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<GuestServiceModel>() {
                }).getBody();
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

    public GuestServiceModel getFallbackUserHomePage(String id) {
        return new GuestServiceModel();
    }

    public GuestServiceModel userHomePage(String id, Throwable throwable) {
        System.out.printf("fallback, thread: %s input:%s, exception:%s%n",
                Thread.currentThread().getName(), id, throwable);
        return new GuestServiceModel();
    }
}
