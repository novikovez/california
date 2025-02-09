package org.california;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class Main {
    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("Europe/Kiev");
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    @PostConstruct
    private static void init() {
        TimeZone.setDefault(DEFAULT_TIME_ZONE);
        Locale.setDefault(DEFAULT_LOCALE);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}