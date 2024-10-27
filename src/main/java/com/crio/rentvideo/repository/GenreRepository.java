package com.crio.rentvideo.repository;

import com.crio.rentvideo.model.Genre;
import com.crio.rentvideo.enums.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenre(GenreType genreType);
}
