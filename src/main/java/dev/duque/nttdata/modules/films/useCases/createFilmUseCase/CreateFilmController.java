package dev.duque.nttdata.modules.films.useCases.createFilmUseCase;

import dev.duque.nttdata.modules.films.dtos.CreateFilmDTO;
import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.exceptions.FilmAlreadyExistsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Api(value = "FilmsController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/films")
public class CreateFilmController {

    private final CreateFilmUseCase createFilmUseCase;

    public CreateFilmController(CreateFilmUseCase createFilmUseCase) {
        this.createFilmUseCase = createFilmUseCase;
    }

    @ApiOperation(value = "Create a new film", response = Film.class)
    @PostMapping
    public ResponseEntity<Object> createFilm(@RequestBody @Valid CreateFilmDTO filmsDTO) throws FilmAlreadyExistsException {

        return ResponseEntity.status(HttpStatus.CREATED).body(createFilmUseCase.execute(filmsDTO));

    }

}
