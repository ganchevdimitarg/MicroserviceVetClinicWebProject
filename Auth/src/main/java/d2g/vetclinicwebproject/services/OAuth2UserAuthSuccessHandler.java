package d2g.vetclinicwebproject.services;

import d2g.vetclinicwebproject.data.model.UserEntity;
import d2g.vetclinicwebproject.services.services.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2UserAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserEntityService userEntityService;
    private final UserDetailsService userDetailsService;

    public OAuth2UserAuthSuccessHandler(UserEntityService userEntityService,
                                        @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userEntityService = userEntityService;
        this.userDetailsService = userDetailsService;
        setDefaultTargetUrl("/home");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        if (authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            String email = oAuth2AuthenticationToken.getPrincipal().getAttribute("email");

            UserEntity userEntity = userEntityService.getOrCreateUser(email);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
