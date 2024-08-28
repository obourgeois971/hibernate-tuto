package com.hibernate4all.tutorial.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hibernate4all.tutorial.domain.Movie;

@Repository
public class MovieRepository {

	//	3 règles d’or
	//	-	Toujours connaitre l’état de la session (c’est savoir si on est ou pas dans une session)
	//	-	Si on est ou pas dans une transaction (c’est généralement lorsque l’on a une écriture dans la BD et avec @Transactional)
	//	-	Toujours se soucier du sql généré par hibernate
	
	/* L'EntityManager permet de dialoguer avec la base de données */
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void persist(Movie movie) {
		// throw new UnsupportedOperationException();
		entityManager.persist(movie);
	}
	
	public List<Movie> getAll() {
		throw new UnsupportedOperationException();
	}
}
