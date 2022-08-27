package dev.duque.nttdata.repositories;

import dev.duque.nttdata.models.FilmsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmsRepository extends JpaRepository<FilmsModel, UUID> {

    Page<FilmsModel> findAll(Pageable pageable);
    Optional<FilmsModel> findByTitle(@Param("title") String title);

    Optional<FilmsModel> findByEpisodeId(Integer episode);

}
