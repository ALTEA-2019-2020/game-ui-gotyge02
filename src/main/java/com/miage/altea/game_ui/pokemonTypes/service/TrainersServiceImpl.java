package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.dto.PokemonDto;
import com.miage.altea.game_ui.dto.TrainerWithPokemonDto;
import com.miage.altea.game_ui.pokemonTypes.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        System.out.println(trainerServiceUrl + "/trainers/" + name);
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
        System.out.println(trainer.name);
        TrainerWithPokemonDto t = new TrainerWithPokemonDto();
        t.setTrainer(new Trainer(trainer.name));
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
    public List<TrainerWithPokemonDto> getAllTrainersWithPokemonDto() {
        List<TrainerWithPokemonDto> trainersDto= new ArrayList<>();
        TrainerWithPokemonDto t;
        for(Trainer trainer : this.restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class)){
            t = new TrainerWithPokemonDto();
            t.setTrainer(new Trainer(trainer.name));
            List<PokemonDto> team = new ArrayList<>();
            for(Pokemon p : trainer.getTeam()) {
                PokemonType pokemonTmp = pokemonTypeServiceImpl.getPokemon(p.getPokemonTypeId());
                PokemonDto pokemonDto = new PokemonDto(pokemonTmp.getName(), p.getLevel(), pokemonTmp.getId(), pokemonTmp.getBaseExperience(), pokemonTmp.getHeight(), pokemonTmp.getSprites(), pokemonTmp.getStats(), pokemonTmp.getWeight(), pokemonTmp.getTypes());
                team.add(pokemonDto);
            }
            t.setTeam(team);
            trainersDto.add(t);
        }
        return trainersDto;
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
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
}
