package dev.duque.nttdata.modules.films.useCases.listAllFilmUseCase;

import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.repositories.FilmsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllFilmsUseCase {

    final FilmsRepository filmsRepository;

    public ListAllFilmsUseCase(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    public Page<Film> execute(Pageable pageable) {
        return filmsRepository.findAll(pageable);
    }

}
