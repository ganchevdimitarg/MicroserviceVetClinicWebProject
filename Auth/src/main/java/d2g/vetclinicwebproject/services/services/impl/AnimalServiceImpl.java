package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.AnimalService;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnimalServiceImpl.class);

    public static final String URL_TO_USER_SERVICE = "http://localhost:8082/user/";

    private final RestTemplate restTemplate;


    @Override
    public List<AnimalServiceModel> getUserAnimals(String username) {
        return restTemplate.exchange(URL_TO_USER_SERVICE + "animals/" + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<AnimalServiceModel>>() {
                }).getBody();
    }

    @Override
    public void addAnimals(AnimalServiceModel animal, String username) {
        restTemplate.postForObject(URL_TO_USER_SERVICE + "animal/add-animal/" + username, animal, AnimalServiceModel.class);
    }

    @Override
    public void deleteUser(String animalId, AnimalServiceModel animal) {
        restTemplate.postForObject(URL_TO_USER_SERVICE + "animal/delete-animal/" + animalId, animal, AnimalServiceModel.class);
    }
}
