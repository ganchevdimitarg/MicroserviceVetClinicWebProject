package d2g.vetclinicwebproject.config;

import d2g.vetclinicwebproject.data.repository.StatsRepository;
import d2g.vetclinicwebproject.handler.StatsLogoutSuccessHandler;
import d2g.vetclinicwebproject.handler.OAuth2UserAuthSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final OAuth2UserAuthSuccessHandler oAuth2UserAuthSuccessHandler;
    private final StatsRepository statsRepository;


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/sign-in", "/register", "/about", "/contact", "/login-error").anonymous()
                .antMatchers("/**")
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/sign-in")
                    .loginProcessingUrl("/sign-in/authenticate")
                    .defaultSuccessUrl("/home",true)
                    .failureForwardUrl("/login-error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/sign-in?logout")
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .successHandler(oAuth2UserAuthSuccessHandler);

    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new StatsLogoutSuccessHandler(statsRepository);
    }
}




