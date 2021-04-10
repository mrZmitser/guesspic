package spring_server.controllers;

import database.Word;
import database.WordDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class WordController {

    @GetMapping("/word")
    public Word word() {
        return WordDao.getRandWord().orElse(new Word("Error"));

    }
}