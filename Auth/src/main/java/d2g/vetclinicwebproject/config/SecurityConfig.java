package d2g.vetclinicwebproject.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
                .antMatchers("/js/**", "/css/**").permitAll()
                .antMatchers("/", "/sign-in", "/register").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .key("remember Me Encryption Key")
                .rememberMeCookieName("rememberMeCookieName")
                .tokenValiditySeconds(10000)
                .and()
                .logout()
                .logoutSuccessUrl("/sign-in?logout")
                .invalidateHttpSession(true)
                .permitAll();

    }
}
