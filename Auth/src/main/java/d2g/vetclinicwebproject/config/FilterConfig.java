package d2g.vetclinicwebproject.config;

import d2g.vetclinicwebproject.fillter.HomeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HomeFilter> restRegistrationBean() {
        FilterRegistrationBean<HomeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new HomeFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/home"));
        return filterRegistrationBean;
    }
}
