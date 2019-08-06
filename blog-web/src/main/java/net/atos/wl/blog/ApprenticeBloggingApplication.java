package net.atos.wl.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entry point for the Apprentice Blogging Application.
 * 
 * <p>
 * The @SpringBootApplication is a convenience annotation that adds all of the
 * following:
 * <ul>
 * <li>@Configuration tags the class as a source of bean definitions for the
 * application context.</li>
 * <li>@EnableAutoConfiguration tells Spring Boot to start adding beans based on
 * classpath settings, other beans, and various property settings.</li>
 * <li>Normally you would add @EnableWebMvc for a Spring MVC app, but Spring
 * Boot adds it automatically when it sees spring-webmvc on the classpath. This
 * flags the application as a web application and activates key behaviors such
 * as setting up a DispatcherServlet.</li>
 * <li>@ComponentScan tells Spring to look for other components, configurations,
 * and services in the package, allowing it to find the controllers.</li>
 * </ul>
 * </p>
 */
@SpringBootApplication
@EnableSwagger2
@ImportResource({"classpath*:blog-web.xml"})
public class ApprenticeBloggingApplication {

    /**
     * The main method uses Spring Boots SpringApplication.run method to launch
     * an application.
     * 
     * @param args
     *            String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(ApprenticeBloggingApplication.class, args);
    }

    /**
     * Method to construct the CORS Filter configuration.
     * 
     * @return <code>org.springframework.boot.web.servlet.FilterRegistrationBean</code>.
     */
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);

        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    /**
     * Swagger configuration to used while generating Swagger API documentation.
     * 
     * @return <code>springfox.documentation.spring.web.plugins.Docket</code>.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
                        .apis(RequestHandlerSelectors.basePackage("net.atos.wl.blog.web.controller"))
                        .paths(PathSelectors.any()).build();
    }

    /**
     * Get the high level API information to be displayed in Swagger API
     * documentation for the service.
     * 
     * @return <code>springfox.documentation.service.ApiInfo</code>.
     */
    private ApiInfo getApiInfo() {
        final Contact contact = new Contact("Rupesh Deshmukh", "https://www.linkedin.com/in/rupeshdeshmukh/",
                        "rupesh.deshmukh@gmail.com");
        return new ApiInfoBuilder().title("Apprentice Blogging Application")
                        .description("Apprentice Blogging Application").version("1.0.0").license("Apache 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").contact(contact).build();
    }
}
