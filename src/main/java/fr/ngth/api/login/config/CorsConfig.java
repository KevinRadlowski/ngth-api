package fr.ngth.api.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.allowedOrigins("https://www.ngth.fr", "https://ngth.fr")
//		.allowedOrigins("http://localhost:4200")
		.allowedHeaders("*");
	}
}