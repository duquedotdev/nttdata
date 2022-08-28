package dev.duque.nttdata.modules.films.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateFilmByEpisodeDTO {

    @NotBlank(message = "The title is mandatory")
    private String title;
    @NotBlank(message = "Opening crawl is required")
    private String openingCrawl;
    @NotBlank(message = "Director is required")
    private String director;
    @NotBlank(message = "Producer is required")
    private String producer;
    @NotBlank(message = "Release date is required")
    private String releaseDate;
    private String url;

}
