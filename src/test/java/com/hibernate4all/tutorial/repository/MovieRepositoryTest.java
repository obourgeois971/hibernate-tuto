package com.hibernate4all.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hibernate4all.tutorial.config.PersistenceConfig;
import com.hibernate4all.tutorial.domain.Movie;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repository;
	
	@Test
	public void save_casNominal() {
		Movie movie = new Movie();
		movie.setName("Inception");
		repository.persist(movie);
	}
}
