package com.aliboucoding.jpa;

import com.aliboucoding.jpa.models.*;
import com.aliboucoding.jpa.repositories.AuthorRepository;
import com.aliboucoding.jpa.repositories.LectureRepository;
import com.aliboucoding.jpa.repositories.ResourceRepository;
import com.aliboucoding.jpa.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	 @Bean
	public CommandLineRunner commandLineRunner(
			ResourceRepository repository,
			LectureRepository lectureRepository
	) {
		return args -> {
			List<Resource> resources = new ArrayList<>();
			Lecture lecture = Lecture.builder().name("Lecture1").build();
			lectureRepository.save(lecture);

			resources.add(File.builder().type("pdf").name("File1").size(100).url("http://example.com/file1").lecture(lecture).build());
			resources.add(Video.builder().length(120).name("Video1").size(200).url("http://example.com/video1").lecture(lecture).build());
			resources.add(Text.builder().content("Sample text content").name("Text1").size(50).url("http://example.com/text1").lecture(lecture).build());
			resources.add(File.builder().type("docx").name("File2").size(150).url("http://example.com/file2").lecture(lecture).build());
			resources.add(Video.builder().length(240).name("Video2").size(300).url("http://example.com/video2").lecture(lecture).build());
			resources.add(Text.builder().content("Another text content").name("Text2").size(60).url("http://example.com/text2").lecture(lecture).build());

			repository.saveAll(resources);

			List<Resource> allResources = repository.findAll();
			for (Resource resource : allResources) {
				System.out.println("Resource ID: " + resource.getId());
				System.out.println("Resource Name: " + resource.getName());
				System.out.println("Resource Size: " + resource.getSize());
				System.out.println("Resource URL: " + resource.getUrl());
				if (resource instanceof File) {
					File file = (File) resource;
					System.out.println("File type: " + file.getType());
				} else if (resource instanceof Video) {
					Video video = (Video) resource;
					System.out.println("Video length: " + video.getLength());
				} else if (resource instanceof Text) {
					Text text = (Text) resource;
					System.out.println("Text content: " + text.getContent());
				}
			}
		};
	}

}
