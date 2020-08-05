package d2g.vetclinicwebproject.event;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

@Getter
public class UpdateCacheEvent extends ApplicationEvent {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateCacheEvent.class);

    private String username;

    public UpdateCacheEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

}
