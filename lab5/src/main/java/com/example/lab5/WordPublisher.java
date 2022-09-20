package com.example.lab5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WordPublisher {
    protected Word words;

    public WordPublisher(){
        this.words = new Word();
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String s){
        words.badWords.add(s);
        return words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s){
        words.badWords.remove(s);
        return words.badWords;
    }

    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
        words.goodWords.add(s);
        return words.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String>  deleteGoodWord(@PathVariable("word") String s){
        words.goodWords.remove(s);
        return words.goodWords;
    }

    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.GET)
    public void  proofSentence(@PathVariable("sentence") String s){
        int goodnum = 0;
        int badnum = 0;

        for(String i : words.goodWords){
            if(s.indexOf(i) != -1){
                goodnum += 1;
                break;
            }
        }

        for(String i : words.badWords){
            if(s.indexOf(i) != -1){
                badnum += 1;
                break;
            }
        }

        if((goodnum > 0) && (badnum > 0)){
            rabbitTemplate.convertAndSend("Fanout", "", s);

        }

        else if(goodnum > 0){
            rabbitTemplate.convertAndSend("Direct", "good", s);

        }

        else if (badnum > 0) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);

        }

    }
}
