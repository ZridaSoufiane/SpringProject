package com.example.zrida_project.repository;

import com.example.zrida_project.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
