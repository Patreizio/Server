
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Questa annotazione combina:
// - @Configuration: indica che questa classe contiene configurazioni Spring
// - @EnableAutoConfiguration: abilita la configurazione automatica di Spring Boot
// - @ComponentScan: abilita la scansione dei componenti nel package corrente e sottopackages
public class DemoApplication {

    public static void main(String[] args) {
        // Metodo di avvio dell'applicazione Spring Boot.
        // SpringApplication.run() inizializza il contesto Spring e avvia il server integrato (Tomcat di default).
        SpringApplication.run(DemoApplication.class, args);
    }
}
