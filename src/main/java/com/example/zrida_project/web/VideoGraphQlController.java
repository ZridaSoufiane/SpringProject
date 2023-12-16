package com.example.zrida_project.web;

import com.example.zrida_project.entities.Creator;
import com.example.zrida_project.entities.Video;
import com.example.zrida_project.repository.CreatorRepository;
import com.example.zrida_project.repository.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


@Controller
    public class VideoGraphQlController {
        private CreatorRepository creatorRepository;
        private VideoRepository videoRepository;
        VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository){
            this.creatorRepository = creatorRepository;
            this.videoRepository = videoRepository;
        }
        @QueryMapping
        public List<Video> videoList(){
            return videoRepository.findAll();
        }
        @QueryMapping
        public Creator creatorById(@Argument Long id) {
            return creatorRepository.findById(id)
                    .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
        }
    @MutationMapping
    public Creator saveCreator(@Argument Creator creator){
        return creatorRepository.save(creator) ;
    }
    @MutationMapping
    public Video saveVideo(@Argument Video video){
        return videoRepository.save(video) ;
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    Creator creatorRequest = Creator.builder().name("x" +
                                    new Random().nextInt())
                            .email("x@gmail.com").build();
                    Creator creator = creatorRepository.save(creatorRequest);
                    Video video = videoRepository.findById(1L).get();
                    video.setCreator(creator);
                    videoRepository.save(video);
                    return video;
                }));
    }


}


