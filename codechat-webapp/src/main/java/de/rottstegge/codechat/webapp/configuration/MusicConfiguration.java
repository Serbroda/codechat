package de.rottstegge.codechat.webapp.configuration;

import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;

import java.io.File;

@Configuration
@EnableFilesystemStores
public class MusicConfiguration {

    File filesystemRoot() {
        return new File("C:\\opt\\music");
    }

    @Bean
    public FileSystemResourceLoader fileSystemResourceLoader() {
        return new FileSystemResourceLoader();
    }

}
