package com.crio.rentvideo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false, unique = false)
  private String title;

  @Column(name = "director", nullable = false, unique = false)
  private String director;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
  private Genre genre;

  @Column(name = "availabilityStatus", nullable = false, unique = false)
  private Boolean availabilityStatus;
}
