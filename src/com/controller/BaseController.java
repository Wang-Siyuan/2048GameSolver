package com.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 11/26/15.
 */
@Controller
@Component
public class BaseController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession) throws UnirestException {
        ModelAndView m = new ModelAndView("/home", "wordDefinition", null);
        return m;
    }

    @RequestMapping(value="/getNextBestMove", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getNextBestMove(@RequestParam MultiValueMap<String, String> map) {
        Logger.getLogger(BaseController.class.getName()).log(Level.INFO, map.toString());
        return "3";
    }

    @RequestMapping(value = "/definition/{word}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    String definition(@PathVariable(value = "word") String word) throws UnirestException {
        String request = "https://wordsapiv1.p.mashape.com/words/" + word + "/definitions";
        HttpResponse<JsonNode> response = Unirest.get(request)
                .header("X-Mashape-Key", "ElQALjRgBzmshJ8jA5PmgxKcDtXbp1ytulzjsnM8LA75nYPMui")
                .header("Accept", "application/json")
                .asJson();
        return response.getBody().toString();
    }
}
