package dev.duque.nttdata.modules.films.useCases.updateFilmUseCase;

import dev.duque.nttdata.modules.films.dtos.UpdateFilmByEpisodeDTO;
import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.exceptions.NotFoundEpisodeException;
import dev.duque.nttdata.modules.films.repositories.FilmsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UpdateFilmByEpisodeUseCase {

    final FilmsRepository filmsRepository;

    public UpdateFilmByEpisodeUseCase(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Film executeWithLock(Integer episode, UpdateFilmByEpisodeDTO dto) {
        Film filmFound = filmsRepository.findByEpisodeId(episode).orElseThrow(() -> new NotFoundEpisodeException());

        BeanUtils.copyProperties(dto, filmFound);

        filmFound.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        // Regra de Negócio
        filmFound.setVersion(filmFound.getVersion() + 1);

        filmsRepository.save(filmFound);

        return filmFound;
    }

}
