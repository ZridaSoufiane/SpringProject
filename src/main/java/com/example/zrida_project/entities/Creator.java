package com.example.zrida_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Creator {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy="creator")
    List<Video>videos;





}
