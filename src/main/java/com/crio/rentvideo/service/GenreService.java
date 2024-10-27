package com.crio.rentvideo.service;

import com.crio.rentvideo.enums.GenreType;
import com.crio.rentvideo.exception.BadRequestException;
import com.crio.rentvideo.model.Genre;
import com.crio.rentvideo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre createGenre(GenreType genreType) {
        Genre genre = new Genre();
        genre.setGenre(genreType);
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, GenreType genreType) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new BadRequestException("Genre not found"));
        genre.setGenre(genreType);
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new BadRequestException("Genre not found");
        }
        genreRepository.deleteById(id);
    }
}
