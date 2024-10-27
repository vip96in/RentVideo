package com.crio.rentvideo.service;

import com.crio.rentvideo.dto.VideoDTO;
import com.crio.rentvideo.exception.BadRequestException;
import com.crio.rentvideo.model.Video;
import com.crio.rentvideo.model.Genre;
import com.crio.rentvideo.repository.VideoRepository;
import com.crio.rentvideo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Method to create a new video
    public Video createVideo(VideoDTO videoDTO) {
        // Fetch the Genre entity based on the GenreType
        Genre genre = genreRepository.findByGenre(videoDTO.getGenre())
                .orElseThrow(() -> new BadRequestException("Genre not found"));

        Video video = new Video();
        video.setTitle(videoDTO.getTitle());
        video.setDirector(videoDTO.getDirector());
        video.setGenre(genre);
        video.setAvailabilityStatus(true);  // Assuming all videos are available to rent

        return videoRepository.save(video);
    }

    // Method to update an existing video
    public Video updateVideo(Long id, VideoDTO videoDTO) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Video not found"));

        video.setTitle(videoDTO.getTitle());
        video.setDirector(videoDTO.getDirector());

        // Retrieve the Genre entity based on the GenreType
        Genre genre = genreRepository.findByGenre(videoDTO.getGenre())
                .orElseThrow(() -> new BadRequestException("Genre not found"));

        video.setGenre(genre);
        return videoRepository.save(video);
    }

    // Method to retrieve a video by ID
    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Video not found"));
    }

    // Method to retrieve all videos
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // Method to delete a video by ID
    public void deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new BadRequestException("Video not found");
        }
        videoRepository.deleteById(id);
    }
}
