package spring_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class QuestionController {

    private static final String template = "Your Question() object is : %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/question")
    public Question greeting(@RequestParam(value = "type", defaultValue = "null") String type,
                                    @RequestParam(value = "cost", defaultValue = "1") String cost) {
        return new Question(counter.incrementAndGet(), type, Integer.parseInt(cost));
    }
}
