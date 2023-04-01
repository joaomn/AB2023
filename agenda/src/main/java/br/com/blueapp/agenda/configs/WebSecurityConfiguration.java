package br.com.blueapp.agenda.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.blueapp.agenda.services.UserDetailsServiceImpls;
import net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record.ForDefinedMethod.WithAnnotationDefaultValue;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpls userDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.formLogin()
		.defaultSuccessUrl("http://localhost:5173")
		.permitAll()
		.and()
		.authorizeHttpRequests()
		.antMatchers("api/login/**").permitAll()
		.antMatchers("/login").permitAll()
		.and()
		.authorizeHttpRequests()
		.anyRequest().permitAll().and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder autenticar) throws Exception {

		autenticar.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

//	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
//		
//		try {
//			http
//			.formLogin((form) -> form
//					.loginPage("/login")
//					.permitAll());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//			return null;
//		}
//		return null;
//		
//		
//		
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
