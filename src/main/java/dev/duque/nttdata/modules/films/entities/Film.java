package dev.duque.nttdata.modules.films.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_FILMS")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(nullable = false, unique = true, length = 50)
    public String title;

    @Column(nullable = false)
    public Integer episodeId;

    @Column(nullable = false)
    public String openingCrawl;

    @Column(nullable = false)
    public String director;

    @Column(nullable = false)
    public String producer;

    @Column(nullable = false)
    public String releaseDate;

    @Column(nullable = true)
    public String url;

    @Column(nullable = false)
    public Integer version;

    @Column(nullable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false)
    public LocalDateTime updatedAt;

}
