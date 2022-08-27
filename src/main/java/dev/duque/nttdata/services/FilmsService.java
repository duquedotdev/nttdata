package dev.duque.nttdata.services;

import dev.duque.nttdata.models.FilmsModel;
import dev.duque.nttdata.repositories.FilmsRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmsService {
    final FilmsRepository filmsRepository;

    public FilmsService(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    public List<FilmsModel> findAll() {
        return filmsRepository.findAll();
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<FilmsModel> findByTitleWithLock(String title) {
        return filmsRepository.findByTitle(title);
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<FilmsModel> findByIdWithLock(UUID id) {
        return filmsRepository.findById(id);
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<FilmsModel> findByEpisodeWithLock(Integer episode) {
        return filmsRepository.findByEpisodeId(episode);
    }

    @Transactional
    public FilmsModel save(FilmsModel filmsModel) {
        return filmsRepository.save(filmsModel);
    }



}
