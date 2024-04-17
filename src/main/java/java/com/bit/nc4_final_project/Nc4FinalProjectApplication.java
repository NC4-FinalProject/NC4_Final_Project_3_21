package java.com.bit.nc4_final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.bit.nc4_final_project.repository",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.bit\\.nc4_final_project\\.repository\\.travel\\.mongo\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.bit\\.nc4_final_project\\.repository\\.chatroom\\..*")
        }
)
public class Nc4FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nc4FinalProjectApplication.class, args);
    }

}
