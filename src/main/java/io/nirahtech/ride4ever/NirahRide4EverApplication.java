package io.nirahtech.ride4ever;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.nirahtech.ride4ever.engine.runtime.ApplicationShutdownHook;
import io.nirahtech.ride4ever.engine.runtime.ApplicationUncaughtExceptionHandler;
import io.nirahtech.ride4ever.infrastructure.interceptors.LogFilter;
import io.nirahtech.ride4ever.microservices.address.AddressService;

/**
 * Class that represents the entry point of the application.
 *
 * @author METIVIER NicolasO
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
        System.out.println((new AddressService()).findAddressByCoordinates(43.68739700317383, 0.45949938893318176));
        System.out.println((new AddressService()).findWeatherByCoordinates(43.68739700317383, 0.45949938893318176));
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
        emfb.setPackagesToScan("io.nirahtech.ride4ever.microservices");
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

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers", "-tcpPort", "8084");
    }

    @Bean
    public FilterRegistrationBean<LogFilter> filterRegistrationBean() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        LogFilter filter = new LogFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        // registrationBean.setOrder(2); // set precedence
        return registrationBean;
    }

}
