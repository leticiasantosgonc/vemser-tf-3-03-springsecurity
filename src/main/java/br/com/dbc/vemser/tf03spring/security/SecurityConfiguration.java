package br.com.dbc.vemser.tf03spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/", "/acesso/cadastrar/usuario").permitAll()
                        .antMatchers("/acesso").permitAll()
//                        .antMatchers(PUT, "/acesso/**").hasRole("ADMIN")
                        .antMatchers("/acesso/retornarusuariologado").hasAnyRole("USER", "ADMIN")
                        .antMatchers(GET,"/aluno/**").hasAnyRole( "USER", "ADMIN")
//                        .antMatchers("/aluno/**").hasRole("ADMIN")
                        .antMatchers(GET,"/professor/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers("/professor/**").hasRole("ADMIN")
                        .antMatchers(GET, "/curso/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers("/curso/**").hasRole("ADMIN")
                        .antMatchers(GET, "/endereco/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers("/endereco/**").hasRole("ADMIN")
                        .antMatchers("/**").hasRole("ADMIN")
                        .anyRequest().denyAll()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

}
