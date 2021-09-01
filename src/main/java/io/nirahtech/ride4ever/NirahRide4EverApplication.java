package io.nirahtech.ride4ever;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.nirahtech.ride4ever.engine.encryption.PasswordEncrypt;
import io.nirahtech.ride4ever.engine.runtime.ApplicationShutdownHook;
import io.nirahtech.ride4ever.engine.runtime.ApplicationUncaughtExceptionHandler;
import io.nirahtech.ride4ever.infrastructure.interceptors.LogFilter;
import io.nirahtech.ride4ever.microservices.address.Address;
import io.nirahtech.ride4ever.microservices.address.AddressService;
import io.nirahtech.ride4ever.microservices.address.Country;
import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;
import io.nirahtech.ride4ever.microservices.biker.Gender;
import io.nirahtech.ride4ever.microservices.motorbike.Brand;
import io.nirahtech.ride4ever.microservices.motorbike.Motorbike;
import io.nirahtech.ride4ever.microservices.motorbike.MotorbikeService;
import io.nirahtech.ride4ever.microservices.motorbike.MotorbikeType;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTrip;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripService;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripStatus;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripType;

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

    @Bean
    public CommandLineRunner mappingBiker(BikerService bikerService,
    MotorbikeService motorbikeService,
    RoadTripService roadTripService,
                                         AddressService addressService) {
        return args -> {

            Address address = new Address();
            address.setNumber(40);
            address.setStreet("Route de Pelleport");
            address.setState("OCCITANIE");
            address.setCity("Le Grès");
            address.setZipCode(31480);
            address.setCountry(Country.FRANCE);
            address = addressService.create(address);

            Biker biker = new Biker();
            biker.setFirstName("Nicolas");
            biker.setLastName("METIVIER");
            biker.setBirthDate(Timestamp.from(Instant.now()));
            biker.setPseudo("Jxalo");
            biker.setEmail("nicolas.a.metivier@gmail.com");
            biker.setGender(Gender.MALE.name());
            biker.setPhoneNumber("06.23.33.57.03");
            biker.setPassword(PasswordEncrypt.encrypt("Ride4Ever"));
            biker.setAddress(address);
            biker = bikerService.create(biker);

            Motorbike motorbike = new Motorbike();
            motorbike.setBrand(Brand.HONDA);
            motorbike.setModel("CBF 600 N ABS");
            motorbike.setYear(2004);
            motorbike.setType(MotorbikeType.ROADSTER);
            motorbike.setEngineDisplacement(600);
            motorbike.setMileage(25000);
            motorbike.setFuelTankSize(12);
            motorbike.setBiker(biker);
            motorbike = motorbikeService.create(motorbike);

            RoadTrip roadTrip = new RoadTrip();
            roadTrip.setTitle("title");
            roadTrip.setDescription("Description");
            roadTrip.setStartDate(Timestamp.from(Instant.now()));
            roadTrip.setEndDate(Timestamp.from(Instant.now()));
            roadTrip.setStartAddress(address);
            roadTrip.setStopAddress(address);
            roadTrip.setKilometersAverage(160);
            roadTrip.setStatus(RoadTripStatus.SOON);
            roadTrip.setRoadTripType(RoadTripType.RELAX);
            roadTrip.setMaxBikers(10);
            roadTrip.setOrganizer(biker);
            roadTrip = roadTripService.create(roadTrip);

        };
    }

}
