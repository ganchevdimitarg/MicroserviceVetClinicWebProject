package d2g.vetclinicwebproject.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UpdateCachePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishUpdateCache(String username){
        UpdateCacheEvent updateCacheEvent = new UpdateCacheEvent(this, username);
        applicationEventPublisher.publishEvent(updateCacheEvent);
    }

}
