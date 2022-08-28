package dev.duque.nttdata.modules.films.useCases.findFilmByEpisode;

import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.useCases.listAllFilmUseCase.ListAllFilmsUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "FilmsController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/films")
public class FindFilmByEpisodeController {
    private FindFilmByEpisodeUseCase findFilmByEpisodeUseCase;

    public FindFilmByEpisodeController(FindFilmByEpisodeUseCase findFilmByEpisodeUseCase) {
        this.findFilmByEpisodeUseCase = findFilmByEpisodeUseCase;
    }

    @ApiOperation(value = "Find a film by episode", response = Film.class)
    @GetMapping("/episode/{episode}")
    public ResponseEntity<Object> findFilmByEpisode(@PathVariable Integer episode){
        return ResponseEntity.status(HttpStatus.OK).body(findFilmByEpisodeUseCase.execute(episode).get());
    }

}
