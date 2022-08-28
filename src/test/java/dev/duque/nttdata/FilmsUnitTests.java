package dev.duque.nttdata;

import dev.duque.nttdata.modules.films.dtos.CreateFilmDTO;
import dev.duque.nttdata.modules.films.dtos.UpdateFilmByEpisodeDTO;
import dev.duque.nttdata.modules.films.entities.Film;
import dev.duque.nttdata.modules.films.repositories.FilmsRepository;
import dev.duque.nttdata.modules.films.useCases.createFilmUseCase.CreateFilmUseCase;
import dev.duque.nttdata.modules.films.useCases.findFilmByEpisode.FindFilmByEpisodeUseCase;
import dev.duque.nttdata.modules.films.useCases.listAllFilmUseCase.ListAllFilmsUseCase;
import dev.duque.nttdata.modules.films.useCases.updateFilmUseCase.UpdateFilmByEpisodeUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Films Unit Tests")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureDataJpa
public class FilmsUnitTests {

	@Autowired
	private FilmsRepository filmsRepository;

	@Autowired
	private CreateFilmUseCase createFilmUseCase;

	@Autowired
	private UpdateFilmByEpisodeUseCase updateFilmByEpisodeUseCase;

	@Autowired
	private ListAllFilmsUseCase listAllFilmsUseCase;

	@Autowired
	private FindFilmByEpisodeUseCase findFilmByEpisodeUseCase;

	@Test
	@DisplayName("Should be able to create a new film")
	public void itShouldBeAbleToCreateANewMovie() throws Exception {

		CreateFilmDTO dto = new CreateFilmDTO();
		dto.setTitle("Teste");
		dto.setEpisodeId(1);
		dto.setOpeningCrawl("Teste");
		dto.setDirector("Teste");
		dto.setProducer("Teste");
		dto.setReleaseDate("Teste");
		dto.setUrl("Teste");

		createFilmUseCase.execute(dto);

		assertEquals(1, filmsRepository.findAll().size());

		assertEquals(1, filmsRepository.findAll().get(0).getEpisodeId());
		assertEquals("Teste", filmsRepository.findAll().get(0).getTitle());
		assertEquals("Teste", filmsRepository.findAll().get(0).getOpeningCrawl());
		assertEquals("Teste", filmsRepository.findAll().get(0).getDirector());
		assertEquals("Teste", filmsRepository.findAll().get(0).getProducer());
		assertEquals("Teste", filmsRepository.findAll().get(0).getReleaseDate());
		assertEquals("Teste", filmsRepository.findAll().get(0).getUrl());
		assertEquals(1, filmsRepository.findAll().get(0).getVersion());
	}

	@Test
	@DisplayName("Should be able to update a film")
	public void itShouldBeAbleToUpdateAFilm() throws Exception {

		CreateFilmDTO dto = new CreateFilmDTO();
		dto.setTitle("Teste");
		dto.setEpisodeId(1);
		dto.setOpeningCrawl("Teste");
		dto.setDirector("Teste");
		dto.setProducer("Teste");
		dto.setReleaseDate("Teste");
		dto.setUrl("Teste");

		createFilmUseCase.execute(dto);

		UpdateFilmByEpisodeDTO updateDto = new UpdateFilmByEpisodeDTO();

		BeanUtils.copyProperties(dto, updateDto);

		updateDto.setTitle("Teste 2");

		updateFilmByEpisodeUseCase.executeWithLock(1, updateDto);

		assertEquals("Teste 2", filmsRepository.findAll().get(1).getTitle());
		assertEquals(2, filmsRepository.findAll().get(1).getVersion());
	}

	@Test
	@DisplayName("Should be able to list all films")
	public void shouldBeAbleToListAllFilms() throws Exception {

		CreateFilmDTO dto = new CreateFilmDTO();
		dto.setTitle("Teste");
		dto.setEpisodeId(1);
		dto.setOpeningCrawl("Teste");
		dto.setDirector("Teste");
		dto.setProducer("Teste");
		dto.setReleaseDate("Teste");
		dto.setUrl("Teste");

		createFilmUseCase.execute(dto);

		Page page = listAllFilmsUseCase.execute(Pageable.unpaged());

		assertEquals(1, page.getTotalElements());
	}

	@Test
	@DisplayName("Should be able to find a film by episode")
	public void shouldBeAbleToFindAFilmByTitle() throws Exception {

		CreateFilmDTO dto = new CreateFilmDTO();
		dto.setTitle("Teste");
		dto.setEpisodeId(1);
		dto.setOpeningCrawl("Teste");
		dto.setDirector("Teste");
		dto.setProducer("Teste");
		dto.setReleaseDate("Teste");
		dto.setUrl("Teste");

		createFilmUseCase.execute(dto);

		assertEquals(1, findFilmByEpisodeUseCase.execute(1).get().getEpisodeId());
		assertEquals(1, findFilmByEpisodeUseCase.execute(1).get().getVersion());
		assertEquals("Teste", findFilmByEpisodeUseCase.execute(1).get().getTitle());
		assertEquals("Teste", findFilmByEpisodeUseCase.execute(1).get().getReleaseDate());
		assertEquals("Teste", findFilmByEpisodeUseCase.execute(1).get().getUrl());
	}

}
