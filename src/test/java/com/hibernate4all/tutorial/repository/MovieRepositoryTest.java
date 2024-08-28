package com.hibernate4all.tutorial.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hibernate4all.tutorial.config.PersistenceConfig;
import com.hibernate4all.tutorial.domain.Movie;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSourceH2", transactionManager = "transactionManager")
@Sql({ "/datas/datas-test.sql" })
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repository;
	
	@Test
	public void save_casNominal() {
		Movie movie = new Movie();
		movie.setName("Inception");
		repository.persist(movie);
		System.out.println("fin de test");
	}
	
	// Pour passer d'un état Detached -> Managed
	@Test
	public void merge_casSimule() {
		Movie movie = new Movie();
		movie.setName("Inception 2");
		movie.setId(-1L);
		Movie mergedMovie = repository.merge(movie);
		assertThat(mergedMovie.getName()).as("le nom du film n'a pas été mis à jour").isEqualTo("Inception 2");
	}
	
	@Test
	public void remove_casNominal() {
		repository.remove(-2L);
		List<Movie> movies = repository.getAll();
		assertThat(movies).as("le film n'a pas été supprimé").hasSize(1);
	}
	
	@Test
	public void find_casNominal() {
		Movie memento = repository.find(-2L);
		assertThat(memento.getName()).as("mauvais film récupéré").isEqualTo("Memento");
	}
	
	@Test
	public void getAll_casNominal() {
		List<Movie> movies = repository.getAll();
		assertThat(movies).as("l'ensemble des films n'a pas été récupéré").hasSize(2);
	}
	
	@Test
	public void getReference_casNominal() {
		Movie movie = repository.getReference(-2L);
		assertThat(movie.getId()).as("la référence n'a pas été correctement chérgée").isEqualTo(-2L);
	}
}
