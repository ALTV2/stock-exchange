//package com.tveritin.config;
//
//import com.tveritin.config.filter.JwtRequestFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig {
//
//    private final UserService userService;
//    private final JwtRequestFilter jwtRequestFilter;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/registration").not().fullyAuthenticated()
//                .antMatchers("/login").not().fullyAuthenticated()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userService);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
////    @Bean
////    public JwtTokenFilter jwtAuthenticationFilter() {
////        return new JwtAuthenticationFilter();
////    }
//
////    @Override
////    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
////    }
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeRequests()
////                //Доступ только для не зарегистрированных пользователей
////                .antMatchers("/registration").not().fullyAuthenticated()
//////                //Доступ только для пользователей с ролью Администратор
//////                .antMatchers("/admin/**").hasRole("ADMIN")
//////                .antMatchers("/news").hasRole("USER")
//////                //Доступ разрешен всем пользователей
//////                .antMatchers("/", "/resources/**").permitAll()
//////                //Все остальные страницы требуют аутентификации
//////                .anyRequest().authenticated()
//////                .and()
//////                //Настройка для входа в систему
//////                .formLogin()
//////                .loginPage("/login")
//////                //Перенарпавление на главную страницу после успешного входа
//////                .defaultSuccessUrl("/")
//////                .permitAll()
//////                .and()
//////                .logout()
//////                .permitAll()
//////                .logoutSuccessUrl("/");
////                .anyRequest().authenticated()
//////                .antMatchers("/**").hasRole("TEAM_LEADER")
////                .and().formLogin();
////    }
////        http.authorizeRequests()
////                .antMatchers("/registration/**").permitAll()// Разрешить доступ ко всем URL, начинающимся с /public/
////                .anyRequest().authenticated()
//////                .antMatchers("/**").hasRole("TEAM_LEADER")
////                .and().formLogin();
//
////        http.csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
////                .antMatchers("/api/auth/**").permitAll().anyRequest().authenticated();
////
////        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//}
