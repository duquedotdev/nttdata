package dev.duque.nttdata.modules.films.repositories;

import dev.duque.nttdata.modules.films.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmsRepository extends JpaRepository<Film, UUID> {

    Optional<Film> findByTitle(@Param("title") String title);

    Optional<Film> findByEpisodeId(Integer episode);

}
