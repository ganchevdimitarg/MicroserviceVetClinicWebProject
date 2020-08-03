package d2g.vetclinicwebproject.config;

import d2g.vetclinicwebproject.interceptor.StatsLoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StatsLoginInterceptor statsLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsLoginInterceptor).addPathPatterns("/**/home/**/");
    }

}
