package io.neczpal.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        // Gets done automatically
        /*
        jdbcTemplate.execute(
                "create table locations (id bigint auto_increment, loc_name varchar(255), lat double, lon double, primary key(id))"
        );

        jdbcTemplate.execute(
                "insert into locations(loc_name, lat, lon) values ('Budapest', 40.5, 17.5)"
        );

        jdbcTemplate.execute(
                "insert into locations(loc_name, lat, lon) values ('Szeged', 44.2, 19.3)"
        );*/
    }
}
