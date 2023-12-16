package com.example.zrida_project;

import com.example.zrida_project.entities.Creator;
import com.example.zrida_project.entities.Video;
import com.example.zrida_project.repository.CreatorRepository;
import com.example.zrida_project.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ZridaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZridaProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CreatorRepository repositoryCreator , VideoRepository repositoryVideo )
	{
		return args -> {
			Creator c = Creator.builder().name("Taha").email("taha@gmail.com").videos(null)
					.build();
			Creator c1 = Creator.builder().name("Soufiane").email("soufiane@gmail.com").videos(null)
					.build();
			Video video = Video.builder().name("video").url("http://video.com").description("video 1").datePublication(new Date()).creator(c)
					.build();
			Video video1 = Video.builder().name("video1").url("http://video1.com").description("video 1111").datePublication(new Date()).creator(c1)
					.build();
			repositoryCreator.save(c);
			repositoryCreator.save(c1);
			repositoryVideo.save(video);
			repositoryVideo.save(video1);
		};
	}

}

