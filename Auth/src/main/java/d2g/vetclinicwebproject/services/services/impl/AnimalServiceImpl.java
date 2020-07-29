package d2g.vetclinicwebproject.services.services.impl;

import d2g.vetclinicwebproject.services.models.AddAnimalServiceModel;
import d2g.vetclinicwebproject.services.services.AnimalService;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    public static final String URL_TO_USER_SERVICE = "http://localhost:8082/user/";

    private final RestTemplate restTemplate;

    public List<AnimalApiControllerModel> getUserAnimals(String username) {
        return restTemplate.exchange(URL_TO_USER_SERVICE + "animals/" + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<AnimalApiControllerModel>>() {
                }).getBody();
    }


    @Override
    public void addAnimals(AddAnimalServiceModel animal, String username) {
        restTemplate.postForObject(URL_TO_USER_SERVICE + "animal/add-animal/" + username, animal, AddAnimalServiceModel.class);
    }

    public void deleteUser(String animalId, AddAnimalServiceModel animal) {
        restTemplate.postForObject(URL_TO_USER_SERVICE + "animal/delete-animal/" + animalId, animal, AddAnimalServiceModel.class);
    }
}
