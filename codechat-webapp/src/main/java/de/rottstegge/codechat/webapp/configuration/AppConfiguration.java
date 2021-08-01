package de.rottstegge.codechat.webapp.configuration;

import de.rottstegge.codechat.webapp.service.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfiguration {

    @Autowired
    public void initializeData(DataInitializer dataInitializer) throws IOException {
        dataInitializer.initialize();
    }
}
