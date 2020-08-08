package d2g.vetclinicwebproject.event;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.AnimalService;
import d2g.vetclinicwebproject.web.api.controllers.AnimalApiController;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateCacheEventListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnimalApiController.class);

    private final AnimalService animalService;
    private final ModelMapper modelMapper;

    public UpdateCacheEventListener(AnimalService animalService, ModelMapper modelMapper) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @CachePut("animals")
    @EventListener(UpdateCacheEvent.class)
    public ResponseEntity<List<AnimalApiControllerModel>> onUpdateCache(UpdateCacheEvent updateCacheEvent){
        animalService.addAnimals(updateCacheEvent.getAnimal(),updateCacheEvent.getUsername());
        LOGGER.info("Update cache...");

        List<AnimalApiControllerModel> models = animalService.getUserAnimals(updateCacheEvent.getUsername())
                .stream()
                .map(animal -> modelMapper.map(animal, AnimalApiControllerModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
