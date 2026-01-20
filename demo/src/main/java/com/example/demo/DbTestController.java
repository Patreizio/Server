
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DbTestController {

    private final DataSource dataSource;

    public DbTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db-test")
    public String testDb() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            return "DB OK – Connesso a: " + conn.getMetaData().getURL();
        }
    }

    @GetMapping("/") // opzionale, per vedere subito qualcosa alla root
    public String home() { return "App up su porta 8080"; }
}
