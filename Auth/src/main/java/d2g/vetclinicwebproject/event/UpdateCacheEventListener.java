package d2g.vetclinicwebproject.event;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.AnimalService;
import d2g.vetclinicwebproject.web.api.controllers.AnimalApiController;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UpdateCacheEventListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnimalApiController.class);

    private final AnimalService animalService;

//    @CachePut("animals")
    @EventListener(UpdateCacheEvent.class)
    public List<AnimalServiceModel> onUpdateCache(UpdateCacheEvent updateCacheEvent){
        List<AnimalServiceModel> userAnimals = animalService.getUserAnimals(updateCacheEvent.getUsername());
        LOGGER.info("Update cache...");

        return userAnimals;
    }
}
