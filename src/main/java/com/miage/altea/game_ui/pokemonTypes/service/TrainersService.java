package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.dto.TrainerWithPokemonDto;
import com.miage.altea.game_ui.pokemonTypes.Trainer;

import java.util.List;

public interface TrainersService {
    List<Trainer> listTrainer();
    TrainerWithPokemonDto getTrainerWithPokemonDto(String name);
    Trainer getTrainer(String name);
    Trainer[] getAllTrainers();
}
