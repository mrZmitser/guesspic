package spring_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"database", "spring_server"})
public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class);
    }
}