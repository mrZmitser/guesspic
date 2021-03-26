import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        var word = new Word();
        System.out.println(word.getWord());
        SpringApplication.run(ServerApp.class);
    }
}