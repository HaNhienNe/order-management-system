package com.sangdd.omsbe.config;

import com.sangdd.omsbe.entity.User;
import com.sangdd.omsbe.repository.UserRepository;
import com.sangdd.omsbe.security.JwtFilter;
import com.sangdd.omsbe.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserService userService;

    // Inject JwtFilter thông qua Constructor (Cách tốt nhất trong Spring)
    public SecurityConfig(JwtFilter jwtFilter, UserService userService) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF vì chúng ta dùng JWT (Stateless)
                .csrf(csrf -> csrf.disable())

                // 2. Cấu hình CORS để Angular có thể gọi API
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 3. Phân quyền các Endpoint
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Cho phép Login/Register không cần Token
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN") // Chỉ Admin mới vào được
                        .anyRequest().authenticated() // Tất cả các yêu cầu khác phải có Token hợp lệ
                )

                // 4. Cấu hình Session là Stateless (Không lưu Session trên Server)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 5. Thêm JwtFilter vào TRƯỚC UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Cấu hình CORS để tránh lỗi khi Angular (port 4200) gọi Spring (port 8080)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Domain của Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Bean mã hóa mật khẩu (Dùng để lưu mật khẩu vào DB một cách an toàn)
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 2. Đảm bảo bạn đã có một UserDetailsService để Spring biết chỗ tìm User (TRÁNH ĐỆ QUY)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Thay đoạn này bằng code tìm user từ UserRepository của bạn
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getRole()) // Quan trọng để phân quyền
                    .build();
        };
    }
}