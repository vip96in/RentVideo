package com.crio.rentvideo.dto;

import com.crio.rentvideo.enums.GenreType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {

    @NotBlank(message = "Title is required")
    @Size(message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Director is required")
    @Size(message = "Director must not exceed 100 characters")
    private String director;

    @NotNull(message = "Genre is required")
    private GenreType genre;

    @NotNull(message = "Availability status is required")
    private Boolean availabilityStatus;
}
