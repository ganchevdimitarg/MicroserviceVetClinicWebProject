package d2g.vetclinicwebproject.scheduling;

import d2g.vetclinicwebproject.services.services.schedule.ScheduleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableAsync
public class SchedulerCleanupScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerCleanupScheduler.class);

    private final ScheduleService scheduleService;

    @Async
    @Scheduled(cron = "0 0 2 ? * SUN")
    protected void scheduleJob(){
        LOGGER.info("All schedules is finished");
        scheduleService.deleteAll();
    }
}

