package io.nirahtech.ride4ever;

import java.sql.Timestamp;

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
import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.core.environment.Blood;
import io.nirahtech.ride4ever.core.environment.Brand;
import io.nirahtech.ride4ever.core.environment.Country;
import io.nirahtech.ride4ever.core.environment.Gender;
import io.nirahtech.ride4ever.core.environment.Motorbike;
import io.nirahtech.ride4ever.core.environment.MotorbikeType;
import io.nirahtech.ride4ever.core.environment.Place;
import io.nirahtech.ride4ever.domain.pilot.PilotService;

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

        final Motorbike cbf125 = new Motorbike();
        cbf125.setBrand(Brand.HONDA);
        cbf125.setModel("CBF 125");
        cbf125.setColor("rgba(0,0,0,1)");
        cbf125.setEngineDisplacement(125);
        cbf125.setFuelTankSize(9);
        cbf125.setLicensePlate("AR-587-BE");
        cbf125.setYear(2010);
        cbf125.setType(MotorbikeType.ROADSTER);
        cbf125.setMileage(18000);
        
        final Motorbike cbf600 = new Motorbike();
        cbf600.setBrand(Brand.HONDA);
        cbf600.setModel("CBF 600 N ABS");
        cbf600.setColor("rgba(100,100,100,1)");
        cbf600.setEngineDisplacement(125);
        cbf600.setFuelTankSize(12);
        cbf600.setLicensePlate("EB-956-FT");
        cbf600.setYear(2004);
        cbf600.setType(MotorbikeType.ROADSTER);
        cbf600.setMileage(23000);

        final Place place = new Place();
        place.setCity("Le Grès");
        place.setCountry(Country.FRANCE);
        place.setNumber(40);
        place.setStreet("Route de Pelleport");
        place.setZipCode(31480);

        final Biker pilot = new Biker();
        pilot.setFirstName("Nicolas");
        pilot.setLastName("METIVIER");
        pilot.setBirthDate(Timestamp.valueOf("1993-01-06 07:00:00"));
        pilot.setDriverLicenceDate(Timestamp.valueOf("2020-06-26 07:00:00"));
        pilot.setRegistrationDate(Timestamp.valueOf("2021-08-12 07:00:00"));
        pilot.setBlood(Blood.O_PLUS);
        pilot.setGender(Gender.MALE.name());
        pilot.setEmail("nicolas.a.metivier@gmail.com");
        pilot.setPhoneNumber("06.23.33.57.03");
        pilot.setBiography("Je suis passioné.");
        pilot.setCanRepairMotorbike(false);
        pilot.setHadHaveOperations(true);
        pilot.setWork("Software Engineer");
        pilot.setPassword("R|d43vR");
        pilot.setPseudo("Jxalo");
        pilot.setPlace(place);

        pilot.getMotorbikes().add(cbf125);
        pilot.getMotorbikes().add(cbf600);

        final PilotService service = PilotService.getInstance();
        try {
            service.create(pilot);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
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
