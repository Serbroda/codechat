package de.rottstegge.codechat.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

import java.util.UUID;

@SpringBootApplication
public class CodechatWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodechatWebappApplication.class, args);
	}

	@StoreRestResource(path = "characterSounds")
	public interface SoundsContextStore extends ContentStore<UUID, String> {

	}

}
