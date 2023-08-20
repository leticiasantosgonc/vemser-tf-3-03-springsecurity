package br.com.dbc.vemser.tf03spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import static br.com.dbc.vemser.tf03spring.security.permissions.Permissions.*;
import static br.com.dbc.vemser.tf03spring.security.roles.Roles.ADMIN;
import static br.com.dbc.vemser.tf03spring.security.roles.Roles.ALUNO;
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
                        .antMatchers("/").permitAll()
                        .antMatchers("/acesso/**").permitAll()

                        //.antMatchers("/contato/**").hasRole(ADMIN.name())
                        //.antMatchers("/curso/**").hasRole(ADMIN.name())
                        //.antMatchers("/endereco/**").hasRole(ADMIN.name())

                        .antMatchers("/aluno/**").hasAnyRole(ALUNO.name(), ADMIN.name())
                        .antMatchers(POST, "/aluno/**").hasAnyAuthority(ALUNO_CREATE.name(), ADMIN_CREATE.name())
                        .antMatchers(GET, "/aluno/**").hasAnyAuthority(ALUNO_READ.name(), ADMIN_READ.name())
                        .antMatchers(PUT, "/aluno/**").hasAnyAuthority(ALUNO_UPDATE.name(), ADMIN_UPDATE.name())
                        .antMatchers(DELETE, "/aluno/**").hasAnyAuthority(ALUNO_DELETE.name(), ADMIN_DELETE.name())

                        .antMatchers(POST, "/professor/**").hasAnyAuthority(PROFESSOR_CREATE.name(), ADMIN_CREATE.name())
                        .antMatchers(GET, "/professor/**").hasAnyAuthority(PROFESSOR_READ.name(), ADMIN_READ.name())
                        .antMatchers(PUT, "/professor/**").hasAnyAuthority(PROFESSOR_UPDATE.name(), ADMIN_UPDATE.name())
                        .antMatchers(DELETE, "/professor/**").hasAnyAuthority(PROFESSOR_DELETE.name(), ADMIN_DELETE.name())

                        .anyRequest().authenticated()
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
