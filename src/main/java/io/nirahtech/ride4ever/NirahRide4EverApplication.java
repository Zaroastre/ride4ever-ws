package io.nirahtech.ride4ever;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.nirahtech.ride4ever.application.runtime.ApplicationShutdownHook;
import io.nirahtech.ride4ever.application.runtime.ApplicationUncaughtExceptionHandler;

/**
 * Class that represents the entry point of the application.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@SpringBootApplication
public class NirahRide4EverApplication {

    /**
     * Default NirahRide4EverApplication Constrcutor.
     */
    public NirahRide4EverApplication() {

    }

    /**
     * Start the application.
     */
    private final void start(final String[] arguments) {
        Runtime.getRuntime().addShutdownHook(new ApplicationShutdownHook());
        Thread.setDefaultUncaughtExceptionHandler(new ApplicationUncaughtExceptionHandler());
        SpringApplication.run(NirahRide4EverApplication.class, arguments);
    }

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] arguments) {
        NirahRide4EverApplication application = new NirahRide4EverApplication();
        application.start(arguments);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("io.nirahtech.ride4ever.core.environment");
        return emfb;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

}
