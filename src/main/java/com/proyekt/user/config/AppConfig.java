package com.proyekt.user.config;

import com.proyekt.user.exception.BaseException;
import com.proyekt.user.exception.ErrorMessage;
import com.proyekt.user.exception.MessageType;
import com.proyekt.user.model.User;
import com.proyekt.user.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


//kodun meqsedi: Spring Security üçün authentication (login) mexanizmini DB-yə bağlamaq və password yoxlamasını təmin etmək.
@Configuration
public class AppConfig {


    @Autowired
    private RepositoryUser repositoryUser;


    @Bean
    public UserDetailsService userDetailsService(){  // Spring Security bundan login zamanı yoxlama üçün istifadə edir

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // (7.0) burada gedib DB den yoxlayir varmi bele bir sey

               Optional<User> optionalUser =  repositoryUser.findByUserName(username);

               if (optionalUser.isPresent()){

                   return optionalUser.get();
               }

                throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND,null));
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
