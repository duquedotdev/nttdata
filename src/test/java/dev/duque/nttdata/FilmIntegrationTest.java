package dev.duque.nttdata;


import dev.duque.nttdata.dtos.FilmsDTO;
import dev.duque.nttdata.models.FilmsModel;
import dev.duque.nttdata.repositories.FilmsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmIntegrationTest {

    @Autowired
    private MockMvc api;

    @MockBean
    private FilmsRepository repository;

    @Test
    public void shouldBeAbleToCreateANewFilm() throws Exception {

        this.api.perform(
                MockMvcRequestBuilders.post("/api/v1/films")
                        .contentType("application/json")
                        .content("{" +
                                "\"title\":\"Teste\"," +
                                "\"episodeId\":1," +
                                "\"openingCrawl\":\"Teste\"," +
                                "\"director\":\"Teste\"," +
                                "\"producer\":\"Teste\"," +
                                "\"releaseDate\":\"Teste\"," +
                                "\"url\":\"Teste\"" +
                                "}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void shouldBeAbleToListAllFilms() throws Exception{

        FilmsDTO dto = new FilmsDTO();
        dto.setTitle("Teste");
        dto.setEpisodeId(1);
        dto.setOpeningCrawl("Teste");
        dto.setDirector("Teste");
        dto.setProducer("Teste");
        dto.setReleaseDate("Teste");
        dto.setUrl("Teste");

        FilmsModel model = new FilmsModel();
        model.version = 1;
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());

        BeanUtils.copyProperties(dto, model);

        when(repository.save(model)).thenReturn(model);


        this
                .api
                .perform(MockMvcRequestBuilders.get("/api/v1/films"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
