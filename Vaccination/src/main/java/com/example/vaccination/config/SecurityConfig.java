package com.example.vaccination.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/hi","vendors/images/**",
                                "/forgot_password_email", "/forgot_password_form","/resend_code",
                                "/reset/**","/resetNew", "/api/v1/auths/reset/**", "vendors/styles/forgotPassword.css", "vendors/scripts/login.js","vendors/scripts/forgot_password.js"
                        , "vendors/scripts/forgotPasswordForm.js", "vendors/scripts/checkPasswordMatch.js")

                        .permitAll() // Cho phép tất cả mọi người truy cập vào những URL này
                        .requestMatchers("/employee","/createemp","/deleteemployee").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/updateemp").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                        .requestMatchers("/createInjectionSchedule","/saveIS","/updateIS","/injectionScheduleList").hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/productall","/vaccineList","/createVaccine","/vaccineEdit","/vaccine/delete","/vaccineUpload").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/vaccineTypeList","/createVaccineType","/updateVaccineType","/delete").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/newslist","/createnews","/update","/delete/**").hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/createCustomer","/saveCustomer","/allCustomer","/deleteCustomers","/updateCustomer").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                        .requestMatchers("/reportInjectionResultChart","/reportInjectionResult","/searchResult").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                        .requestMatchers("/injectionResult","/createInjectionResult","/injectionResultDelete","/injectionResultDeleteWithCheckbox",
                                "/injectionResultEdit","updateInjectionResult").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                        .anyRequest() // Tất cả các request còn lại cần phải xác thực mới được truy cập
                        .authenticated())

                .exceptionHandling(customizer -> customizer.accessDeniedHandler(accessDeniedHandler())) // Cấu hình xử lý các ngoại lệ liên quan đến quyền truy cập

                .formLogin(form -> form // Cấu hình xác thực dựa trên biểu mẫu (form-based authentication)
                        .loginPage("/login") // Xác định trang đăng nhập của ứng dụng
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("username") //  Xác định tên của các trường USERNAME trong biểu mẫu HTML
                        .passwordParameter("password") // Xác định tên của các trường PASSWORD trong biểu mẫu HTML
                        .defaultSuccessUrl("/home")) // URL mặc định sau khi đăng nhập thành công)

                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để xử lý quá trình đăng xuất
                        .logoutSuccessUrl("/login" + "?logout") // URL mặc định sau khi đăng xuất thành công
                        .invalidateHttpSession(true) // Hủy bỏ phiên làm việc của người dùng sau khi đăng xuất
                        .clearAuthentication(true) // Xóa thông tin xác thực của người dùng sau khi đăng xuất
                        .deleteCookies("JSESSIONID")) // Xóa cookie JSESSIONID sau khi đăng xuất

                .sessionManagement(session -> session // Cấu hình quản lý phiên làm việc
                        .maximumSessions(1) // giới hạn số phiên đăng nhập đồng thời của một người dùng

                        .maxSessionsPreventsLogin(true))
                .build();
    }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
            AccessDeniedHandlerImpl handler = new AccessDeniedHandlerImpl();
            handler.setErrorPage("/access-denied");
            return handler;
        }

    }
