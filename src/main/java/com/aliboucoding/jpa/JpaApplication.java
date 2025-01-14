package com.aliboucoding.jpa;

import com.aliboucoding.jpa.models.*;
import com.aliboucoding.jpa.repositories.AuthorRepository;
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
			ResourceRepository repository
	) {
		return args -> {

			List<Resource> resources = new ArrayList<>();

			resources.add(File.builder().name("File1").size(1024).url("http://example.com/file1.pdf").type("pdf").build());
			resources.add(Video.builder().name("Video1").size(2048).url("http://example.com/video1.mp4").length(120).build());
			resources.add(File.builder().name("File2").size(1024).url("http://example.com/file2.pdf").type("pdf").build());
			resources.add(Video.builder().name("Video2").size(2048).url("http://example.com/video2.mp4").length(120).build());
			resources.add(Text.builder().name("Text1").size(512).url("http://example.com/text1").content("Sample text content").build());
			resources.add(File.builder().name("File3").size(1024).url("http://example.com/file3.docx").type("docx").build());
			resources.add(Video.builder().name("Video3").size(4096).url("http://example.com/video3.mp4").length(240).build());
			resources.add(Text.builder().name("Text2").size(512).url("http://example.com/text2").content("Another text content").build());

			repository.saveAll(resources);

			List<Resource> allResources = repository.findAll();
			for (Resource resource : allResources) {
				if (resource instanceof File) {
					File file = (File) resource;
					System.out.println("File type: " + file.getType());
				} else if (resource instanceof Video) {
					Video video = (Video) resource;
					System.out.println("Video length: " + video.getLength());
				} else if (resource instanceof Text) {
					Text text = (Text) resource;
					System.out.println("Text content: " + text.getContent());
				} else {
					System.out.println("Unknown resource type");
				}
			}
		};

	}

}
