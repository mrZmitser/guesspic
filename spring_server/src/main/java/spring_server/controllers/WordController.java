package spring_server.controllers;

import database.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@RestController
public class WordController {
    @Autowired
    private ApplicationContext appContext;

    @GetMapping("/word")
    public Word word() throws SQLException {
        return appContext.getBean(Word.class);
    }
}