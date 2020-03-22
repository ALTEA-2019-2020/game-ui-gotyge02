package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
    private TrainersService trainersService;

    @GetMapping(value = "/")
    public ModelAndView profil(){
        var modelAndView = new ModelAndView("profile");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("trainer", trainersService.getTrainer(principal.getUsername()));
        modelAndView.addObject("trainers", trainersService.getAllTrainers());
        return modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainersService trainersService) {
        this.trainersService = trainersService;
    }
}