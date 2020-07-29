package d2g.vetclinicwebproject.scheduling;

import d2g.vetclinicwebproject.services.services.schedule.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableAsync
public class SchedulerCleanupScheduler {

    private final ScheduleService scheduleService;

    @Async
    @Scheduled(cron = "0 0 2 ? * SUN")
    protected void scheduleJob(){
        scheduleService.deleteAll();
    }
}
