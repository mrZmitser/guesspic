package spring_server.controllers;

import database.Word;
import database.WordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class WordController {
    @Autowired
    private WordDao wordDao;

    @GetMapping("/word")
    public Word word() {
        return wordDao.getRandWord().orElse(new Word("Error"));
    }
}