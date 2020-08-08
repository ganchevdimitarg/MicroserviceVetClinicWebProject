package d2g.vetclinicwebproject.event;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

@Getter
public class UpdateCacheEvent extends ApplicationEvent {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateCacheEvent.class);

    private final String username;
    private final AnimalServiceModel animal;

    public UpdateCacheEvent(Object source, String username, AnimalServiceModel animal) {
        super(source);
        this.username = username;
        this.animal = animal;
    }

}
