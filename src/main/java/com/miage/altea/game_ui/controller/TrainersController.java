package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainersController {
    public TrainersService trainersService;

    @GetMapping(value = "/trainers")
    public ModelAndView trainers(){
        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainersService.listTrainer());
        return modelAndView;
    }

    @GetMapping(value = "/trainer/{name}")
    public ModelAndView trainer(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");
        var trainer=trainersService.getTrainerWithPokemonDto(name);
        modelAndView.addObject("trainer", trainer);
        return modelAndView;
    }

    @Autowired
    void setTrainersService(TrainersService trainersService) {
        this.trainersService = trainersService;
    }
}
