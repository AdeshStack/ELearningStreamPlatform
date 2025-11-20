package com.course.service.course_service.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 5000)
    private String description;

    // reference to user-service user id (logical relation)
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    private Instant createdAt;

    private Instant updatedAt;


    @ElementCollection //This creates a separate table to store these values because primitive collections cannot be stored directly in the main entity table.
    @CollectionTable(name = "course_videos", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "video_id")
    private List<String> videos=new ArrayList<>();
    @PrePersist
    public void onCreate() { createdAt = Instant.now(); }

    @PreUpdate
    public void onUpdate() { updatedAt = Instant.now(); }
}
