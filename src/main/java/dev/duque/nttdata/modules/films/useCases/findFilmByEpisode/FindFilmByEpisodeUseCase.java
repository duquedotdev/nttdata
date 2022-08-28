package dev.duque.nttdata.modules.films.useCases.findFilmByEpisode;


import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.exceptions.NotFoundEpisodeException;
import dev.duque.nttdata.modules.films.repositories.FilmsRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import java.util.Optional;

@Service
public class FindFilmByEpisodeUseCase {

    final FilmsRepository filmsRepository;

    public FindFilmByEpisodeUseCase(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<Film> execute(Integer episode) {
        Film film = filmsRepository.findByEpisodeId(episode).orElseThrow(() -> new NotFoundEpisodeException());
        return Optional.ofNullable(film);
    }
}
