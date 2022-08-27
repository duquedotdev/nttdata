package dev.duque.nttdata;

import dev.duque.nttdata.dtos.FilmsDTO;
import dev.duque.nttdata.models.FilmsModel;
import dev.duque.nttdata.repositories.FilmsRepository;
import dev.duque.nttdata.services.FilmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
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
	private FilmsService service;

	@Test
	@DisplayName("Should be able to create a new film")
	public void itShouldBeAbleToCreateANewMovie() throws Exception {

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

		service.save(model);

		assertEquals(1, service.findAll().size());

		assertEquals(1, service.findAll().get(0).getEpisodeId());
		assertEquals("Teste", service.findAll().get(0).getTitle());
		assertEquals("Teste", service.findAll().get(0).getOpeningCrawl());
		assertEquals("Teste", service.findAll().get(0).getDirector());
		assertEquals("Teste", service.findAll().get(0).getProducer());
		assertEquals("Teste", service.findAll().get(0).getReleaseDate());
		assertEquals("Teste", service.findAll().get(0).getUrl());
		assertEquals(1, service.findAll().get(0).getVersion());
	}

	@Test
	@DisplayName("Should be able to update a film")
	public void itShouldBeAbleToUpdateAFilm() throws Exception {

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

		service.save(model);

		FilmsModel model2 = service.findByTitleWithLock("Teste").orElse(null);

		model2.setTitle("Teste2");

		service.save(model2);

		assertEquals("Teste2", service.findByTitleWithLock("Teste2").orElse(null).getTitle());

	}

	@Test
	@DisplayName("Should be able to list all films")
	public void shouldBeAbleToListAllFilms() throws Exception {

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

		service.save(model);

		assertEquals(1, service.findAll().size());
		assertEquals(1, service.findAll().get(0).getEpisodeId());
		assertEquals("Teste", service.findAll().get(0).getTitle());
		assertEquals("Teste", service.findAll().get(0).getOpeningCrawl());
		assertEquals("Teste", service.findAll().get(0).getDirector());
		assertEquals("Teste", service.findAll().get(0).getProducer());
		assertEquals("Teste", service.findAll().get(0).getReleaseDate());
		assertEquals("Teste", service.findAll().get(0).getUrl());
		assertEquals(1, service.findAll().get(0).getVersion());

	}

	@Test
	@DisplayName("Should be able to find a film by title")
	public void shouldBeAbleToFindAFilmByTitle() throws Exception {

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

		service.save(model);

		assertEquals(1, service.findByTitleWithLock("Teste").get().getEpisodeId());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getTitle());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getOpeningCrawl());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getDirector());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getProducer());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getReleaseDate());
		assertEquals("Teste", service.findByTitleWithLock("Teste").get().getUrl());
		assertEquals(1, service.findByTitleWithLock("Teste").get().getVersion());

	}

	@Test
	@DisplayName("Should be able to find a film by episode id")
	public void shouldBeAbleToFindAFilmByEpisodeId() throws Exception {

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

		service.save(model);

		assertEquals(1, service.findByEpisodeWithLock(1).get().getEpisodeId());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getTitle());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getOpeningCrawl());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getDirector());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getProducer());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getReleaseDate());
		assertEquals("Teste", service.findByEpisodeWithLock(1).get().getUrl());
		assertEquals(1, service.findByEpisodeWithLock(1).get().getVersion());

	}





}
