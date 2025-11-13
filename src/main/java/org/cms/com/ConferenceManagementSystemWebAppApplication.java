package org.cms.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferenceManagementSystemWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagementSystemWebAppApplication.class, args);
        System.out.println("Conference Management System Web App Started");
    }

}
