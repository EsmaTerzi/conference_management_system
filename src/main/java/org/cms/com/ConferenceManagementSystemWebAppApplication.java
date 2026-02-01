package org.cms.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "org.cms.com")
public class ConferenceManagementSystemWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagementSystemWebAppApplication.class, args);
        System.out.println("Conference Management System Web App Started");
    }

}
