package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    @Override
    public List<PokemonType> listPokemonsTypes() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity entity = new HttpEntity(headers);
        PokemonType[] pts = restTemplate.exchange(pokemonServiceUrl+"/pokemon-types/",HttpMethod.GET,entity,PokemonType[].class).getBody();
        return Arrays.asList(pts);
    }

    @Override
    public List<PokemonType> listPokemonsTypes(List<Integer> ids) {
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity entity = new HttpEntity(headers);
        List<PokemonType> pokemonTypes = new ArrayList<>();
        ids.stream().forEach(pokemonId ->
                pokemonTypes
                        .add(restTemplate.exchange(
                                pokemonServiceUrl+"/pokemon-types/"+pokemonId,
                                HttpMethod.GET,
                                entity,
                                PokemonType.class)
                        .getBody())
        );
        return pokemonTypes;
    }

    public PokemonType getPokemon(int id) {
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(pokemonServiceUrl + "/pokemon-types/"+id,HttpMethod.GET,entity,PokemonType.class).getBody();
    }

    @Autowired
    @Qualifier("pokemonApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

}