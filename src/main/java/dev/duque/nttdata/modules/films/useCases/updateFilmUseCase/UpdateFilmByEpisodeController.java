package dev.duque.nttdata.modules.films.useCases.updateFilmUseCase;


import dev.duque.nttdata.modules.films.dtos.UpdateFilmByEpisodeDTO;
import dev.duque.nttdata.modules.films.entities.Film;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "FilmsController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/films")
public class UpdateFilmByEpisodeController {

    private UpdateFilmByEpisodeUseCase updateFilmByEpisodeUseCase;

    public UpdateFilmByEpisodeController(UpdateFilmByEpisodeUseCase updateFilmByEpisodeUseCase) {
        this.updateFilmByEpisodeUseCase = updateFilmByEpisodeUseCase;
    }

    @ApiOperation(value = "Update a film by episode", response = Film.class)
    @PutMapping("/episode/{episode}")
    public ResponseEntity<Object> updateFilmByEpisode(@PathVariable Integer episode, @RequestBody UpdateFilmByEpisodeDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(updateFilmByEpisodeUseCase.executeWithLock(episode, dto));
    }

}
