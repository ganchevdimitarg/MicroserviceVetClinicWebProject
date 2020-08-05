package d2g.vetclinicwebproject.scheduleTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteCacheSchedule {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteCacheSchedule.class);

    @Scheduled(cron = "0 0 2 ? * SUN")
    @CacheEvict(cacheNames = "animals", allEntries = true)
    public void evictCache() {
        LOGGER.info("Delete caches!");
    }
}
