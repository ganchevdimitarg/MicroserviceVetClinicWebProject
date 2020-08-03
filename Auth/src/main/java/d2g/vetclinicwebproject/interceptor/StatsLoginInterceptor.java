package d2g.vetclinicwebproject.interceptor;

import d2g.vetclinicwebproject.data.model.StatsEntity;
import d2g.vetclinicwebproject.data.repository.StatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class StatsLoginInterceptor implements HandlerInterceptor {

    private final StatsRepository statsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime login = LocalDateTime.now();
        String username = request.getUserPrincipal().getName();
        if (!statsRepository.findByUsername(username).isPresent()) {
            StatsEntity statsEntity = new StatsEntity();
            statsEntity.setUsername(username);
            statsEntity.setLogin(formatter.format(login));
            statsRepository.saveAndFlush(statsEntity);
        }
        return true;
    }
}
