package spring_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"database", "spring_server"})
public class ServerApp {

    public static void main(String[] args) {
        //WordDao.migrate();
        SpringApplication.run(ServerApp.class);
    }

}