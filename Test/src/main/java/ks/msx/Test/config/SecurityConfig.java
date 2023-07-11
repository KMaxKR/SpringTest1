package ks.msx.Test.config;


import ks.msx.Test.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api").permitAll()
                        .requestMatchers("/api/user")//.hasAuthority(Authority.READ.name())
                        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/**")//.hasAuthority(Authority.WRITE.name())
                        .hasRole(Role.ADMIN.name())
                ).formLogin(form -> form.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles(Role.USER.getAuthority())
                //.authorities(Role.USER.getAuthority())
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                //.authorities(Role.ADMIN.getAuthority())
                .roles(Role.ADMIN.getAuthority())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
