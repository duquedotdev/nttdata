package dev.duque.nttdata.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_FILMS")
public class FilmsModel {

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
