package com.example.config;

import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfig {

    @Autowired
    private EmployeeService employeeService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider
                =new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.employeeService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable().authorizeHttpRequests()//.requestMatchers(HttpMethod.GET ,"/student/hello").permitAll()
               // .requestMatchers("/student/authenticate").permitAll()
                .antMatchers("/api/v1/save").permitAll()
                .antMatchers("/api/v1/**")
                .authenticated()
                .and()
               // .sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              //  .and()
                .oauth2Login(oauth2->{
                    oauth2.loginPage("/oauth2/authorization/vempalli-client-oidc");
                })
                .oauth2Client(Customizer.withDefaults())
                ;

       // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return  new BCryptPasswordEncoder();

    }

}
