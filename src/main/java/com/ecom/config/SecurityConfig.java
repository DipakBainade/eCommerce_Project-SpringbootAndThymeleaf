package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ecom.repository.UserRepository;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private AuthenticationSuccessHandler autheticationSuccessHandler;
	
    private final UserDetailsService userDetailsService;
    
    @Autowired
    @Lazy
    private AuthFailureHandlerImpl authenticationFailureHandler;
    
    
    

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	

//	public UserDetailsService userDetailsService()
//	{
//		return new UserDetailsServiceImpl();
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	private UserDetailsService UserDetailsService() {
		// TODO Auto-generated method stub
		return null;
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
		    .authorizeHttpRequests(req->req.requestMatchers("/user/**").hasRole("USER")
		    .requestMatchers("/admin/**").hasRole("ADMIN")
		    .requestMatchers("/**").permitAll())
		    .formLogin(form->form.loginPage("/signin")
		    		.loginProcessingUrl("/login")
//		    		.defaultSuccessUrl("/")
		    		.failureHandler(authenticationFailureHandler)
		    		.successHandler(autheticationSuccessHandler))
		    
		    
		    .logout(logout->logout.permitAll());	
		    		
		return http.build();
	}

}
