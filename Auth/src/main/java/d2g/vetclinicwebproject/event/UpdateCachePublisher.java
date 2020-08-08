package d2g.vetclinicwebproject.event;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UpdateCachePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishUpdateCache(String username, AnimalServiceModel animal){
        UpdateCacheEvent updateCacheEvent = new UpdateCacheEvent(this, username, animal);
        applicationEventPublisher.publishEvent(updateCacheEvent);
    }

}
