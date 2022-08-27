package dev.duque.nttdata.controllers;


import dev.duque.nttdata.dtos.FilmsDTO;
import dev.duque.nttdata.models.FilmsModel;
import dev.duque.nttdata.services.FilmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Api(value = "PeopleController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/films")
public class FilmsController {

    private final FilmsService filmsService;

    public FilmsController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @ApiOperation(value = "Create a new film")
    @PostMapping
    public ResponseEntity<Object> createFilm(@RequestBody @Valid FilmsDTO filmsDTO){


        FilmsModel filmsModel = new FilmsModel();

        if(!filmsService.findByTitleWithLock(filmsDTO.getTitle()).isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Film already exists");
        }

        BeanUtils.copyProperties(filmsDTO, filmsModel);
        filmsModel.setVersion(1);
        filmsModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        filmsModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(filmsService.save(filmsModel));
    }

    @ApiOperation(value = "List all films")
    @GetMapping
    public ResponseEntity<Object> listAllFilms(){
        return ResponseEntity.status(HttpStatus.OK).body(filmsService.findAll());
    }

    @ApiOperation(value = "Find film by id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findFilmById(@PathVariable("id") UUID id){

        Optional<FilmsModel> filmsModelOptional = filmsService.findByIdWithLock(id);

        if(!filmsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmsModelOptional.get());
    }

    @ApiOperation(value = "Find film by title")
    @GetMapping("/title/{title}")
    public ResponseEntity<Object> findFilmByTitle(@PathVariable("title") String title){

        Optional<FilmsModel> filmsModelOptional = filmsService.findByTitleWithLock(title);

        if(!filmsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmsModelOptional.get());
    }

    @ApiOperation(value = "Update film by id")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFilm(@PathVariable("id") UUID id, @RequestBody @Valid FilmsDTO filmsDTO){

        Optional<FilmsModel> filmsModelOptional = filmsService.findByIdWithLock(id);

        if(!filmsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found");
        }

        FilmsModel filmsModel = new FilmsModel();

        BeanUtils.copyProperties(filmsModelOptional.get(), filmsModel);
        BeanUtils.copyProperties(filmsDTO, filmsModel);

        filmsModel.setId(filmsModelOptional.get().getId());
        filmsModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        filmsModel.setVersion(filmsModel.getVersion() + 1);

        return ResponseEntity.status(HttpStatus.OK).body(filmsService.save(filmsModel));
    }

    @ApiOperation(value = "Film by episode")
    @GetMapping("/episode/{episode}")
    public ResponseEntity<Object> findFilmByEpisode(@PathVariable("episode") Integer episode){

        Optional<FilmsModel> filmsModelOptional = filmsService.findByEpisodeWithLock(episode);

        if(!filmsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmsModelOptional.get());
    }

}
