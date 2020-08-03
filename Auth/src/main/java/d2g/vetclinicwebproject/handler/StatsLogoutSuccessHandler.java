package d2g.vetclinicwebproject.handler;

import d2g.vetclinicwebproject.data.model.StatsEntity;
import d2g.vetclinicwebproject.data.repository.StatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
public class StatsLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    private final StatsRepository statsRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime login = LocalDateTime.now();
        StatsEntity statsEntity = statsRepository.findByUsername(authentication.getName()).orElse(new StatsEntity());
        statsEntity.setLogout(formatter.format(login));
        statsRepository.saveAndFlush(statsEntity);

        super.onLogoutSuccess(request, response, authentication);
    }
}
