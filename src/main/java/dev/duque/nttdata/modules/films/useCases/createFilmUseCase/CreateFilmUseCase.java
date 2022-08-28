package dev.duque.nttdata.modules.films.useCases.createFilmUseCase;

import dev.duque.nttdata.modules.films.dtos.CreateFilmDTO;
import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.exceptions.FilmAlreadyExistsException;
import dev.duque.nttdata.modules.films.repositories.FilmsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CreateFilmUseCase {

    final FilmsRepository filmsRepository;

    public CreateFilmUseCase(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    @Transactional
    public Film execute(CreateFilmDTO dto) throws FilmAlreadyExistsException {

        Film film = new Film();

        // Regra de Negócio = Não devem existir filmes com o mesmo título
        if(filmsRepository.findByTitle(dto.getTitle()).isPresent()) {
            throw new FilmAlreadyExistsException();
        }

        // Regra de Negócio = Não devem existir filmes com o mesmo episódio
        if(filmsRepository.findByEpisodeId(dto.getEpisodeId()).isPresent()) {
            throw new FilmAlreadyExistsException();
        }


        BeanUtils.copyProperties(dto, film);

        film.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        film.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        film.setVersion(1);

        filmsRepository.save(film);

        return film;

    }

}
