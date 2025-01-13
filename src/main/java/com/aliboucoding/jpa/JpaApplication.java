package com.aliboucoding.jpa;

import com.aliboucoding.jpa.models.*;
import com.aliboucoding.jpa.repositories.*;
import com.aliboucoding.jpa.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			ResourceRepository resourceRepository,
			VideoRepository videoRepository,
			TextRepository textRepository,
			FileRepository fileRepository
	) {
		return args -> {
			Faker faker = new Faker();

			// Insert multiple Text entities
			List<Text> texts = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				texts.add(Text.builder()
						.content(faker.lorem().paragraph())
						.name(faker.name().name())
						.size(faker.number().numberBetween(1, 1000))
						.url(faker.internet().url())
						.build());
			}
			textRepository.saveAll(texts);

			// Insert multiple File entities
			List<File> files = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				files.add(File.builder()
						.type(faker.file().extension())
						.name(faker.name().name())
						.size(faker.number().numberBetween(1, 1000))
						.url(faker.internet().url())
						.build());
			}
			fileRepository.saveAll(files);

			// Insert multiple Video entities
			List<Video> videos = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				videos.add(Video.builder()
						.name(faker.name().name())
						.size(faker.number().numberBetween(1, 1000))
						.length(faker.number().numberBetween(1, 120))
						.url(faker.internet().url())
						.build());
			}
			videoRepository.saveAll(videos);



			List<Resource> resources = resourceRepository.findAll();
			for (Resource resource : resources) {
				if (resource instanceof File) {
					File file = (File) resource;
					System.out.println("File type: " + file.getType());
				} else if (resource instanceof Video) {
					Video video = (Video) resource;
					System.out.println("Video length: " + video.getLength());
				}
			}

		};
	}

}
