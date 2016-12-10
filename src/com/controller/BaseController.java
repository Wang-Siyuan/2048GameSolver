package com.controller;

import com.ai.MinimaxGameManager;
import com.ai.model.Direction;
import com.ai.model.GameState;
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
    MinimaxGameManager minimaxGameManager = new MinimaxGameManager();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession) {
        ModelAndView m = new ModelAndView("/home", "wordDefinition", null);
        return m;
    }

    @RequestMapping(value="/getNextBestMove", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getNextBestMove(@RequestParam MultiValueMap<String, String> requestMap) {
        GameState gameState = new GameState(requestMap.getFirst("gameState"));
        Direction direction = minimaxGameManager.getNextBestMoveForUser(gameState);
//        Logger.getLogger(BaseController.class.getName()).log(Level.INFO, direction.name());
        return Integer.toString(direction.numVal);
    }
}
