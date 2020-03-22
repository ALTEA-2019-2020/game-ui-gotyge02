package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.dto.PokemonDto;
import com.miage.altea.game_ui.dto.TrainerWithPokemonDto;
import com.miage.altea.game_ui.pokemonTypes.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainersServiceImpl implements TrainersService {

    public RestTemplate restTemplate;
    public String trainerServiceUrl;
    public PokemonTypeServiceImpl pokemonTypeServiceImpl;

    public List<Trainer> listTrainer() {
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));
    }

    public Trainer getTrainer(String name) {
        return restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
    }

    public TrainerWithPokemonDto getTrainerWithPokemonDto(String name) {
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
        TrainerWithPokemonDto t = new TrainerWithPokemonDto();
        t.getTrainer().setName(trainer.getName());
        List<PokemonDto> team = new ArrayList<>();
        for(Pokemon p : trainer.getTeam()) {
            PokemonType pokemonTmp = pokemonTypeServiceImpl.getPokemon(p.getPokemonTypeId());
            PokemonDto pokemonDto = new PokemonDto(pokemonTmp.getName(), p.getLevel(), pokemonTmp.getId(), pokemonTmp.getBaseExperience(), pokemonTmp.getHeight(), pokemonTmp.getSprites(), pokemonTmp.getStats(), pokemonTmp.getWeight(), pokemonTmp.getTypes());
            team.add(pokemonDto);
        }
        t.setTeam(team);
        return t;
    }

    @Override
    public Trainer[] getAllTrainers() {
        return this.restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class);
    }

    @Autowired
    void setPokemonTypeServiceImpl(PokemonTypeServiceImpl pokemonTypeServiceImpl) {
        this.pokemonTypeServiceImpl = pokemonTypeServiceImpl;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
}
