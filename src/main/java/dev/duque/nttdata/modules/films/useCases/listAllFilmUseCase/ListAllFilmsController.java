package dev.duque.nttdata.modules.films.useCases.listAllFilmUseCase;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "FilmsController")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/films")
public class ListAllFilmsController {

    private ListAllFilmsUseCase listAllFilmsUseCase;

    public ListAllFilmsController(ListAllFilmsUseCase listAllFilmsUseCase) {
        this.listAllFilmsUseCase = listAllFilmsUseCase;
    }


    @ApiOperation(value = "List all films", response = Pageable.class)
    @GetMapping
    public ResponseEntity<Object> listAllFilms(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(listAllFilmsUseCase.execute(pageable));
    }

}
