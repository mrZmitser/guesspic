package by.guesspic;

import by.guesspic.dao.WordDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        WordDao.migrate();
        SpringApplication.run(ServerApp.class);
    }

}