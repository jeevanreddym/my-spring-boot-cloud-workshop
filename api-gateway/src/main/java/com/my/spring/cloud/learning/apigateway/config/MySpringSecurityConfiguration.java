package com.my.spring.cloud.learning.apigateway.config;


import org.springframework.context.annotation.Configuration;



@Configuration
//@EnableWebFluxSecurity
public class MySpringSecurityConfiguration {
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER, ADMIN")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    *//**
     * Authorization.
     *//*
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.antMatchers("/").permitAll();
                    auth.antMatchers("/admin").hasRole("ADMIN");
                    auth.antMatchers("/user").hasAnyRole("USER", "ADMIN");
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }






    public void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("blah")
//                .password("blah")
//                .roles("USER")
//                .and()
//                .withUser("foo")
//                .password("foo")
//                .roles("ADMIN");


//        auth.jdbcAuthentication()
//                .dataSource(dataSource())
//                .withDefaultSchema()
//                .withUser(
//                    User.withUsername("user")
//                        .password("pass")
//                        .roles("USER")
//                )
//                .withUser(
//                    User.withUsername("admin")
//                        .password("pass")
//                        .roles("ADMIN")
//                );


        auth.userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        if ("foo".equals(username)) {
                            return new User("foo", "foo", new ArrayList<>());
                        } else {
                            throw new UsernameNotFoundException("User not found with username: " + username);
                        }
                    }
                })
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }*/

}
