package io.neczpal.locations_spring.persistence;

import io.neczpal.locations_spring.entities.Location;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class LocationDao {

    private JdbcTemplate jdbcTemplate;

    public List<Location> findAll() {
        return jdbcTemplate.query("select id, loc_name, lat, lon from locations",
                LocationDao::mapRow);
    }

    public Location findById(long id) {
        return jdbcTemplate.queryForObject("select id, loc_name, lat, lon from locations where id = ?",
                LocationDao::mapRow,
                id);
    }


    public void save(Location location) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into locations(loc_name, lat, lon) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getName());
            ps.setDouble(2, location.getLat());
            ps.setDouble(3, location.getLon());
            return ps;
        }, keyHolder);
        location.setId(keyHolder.getKey().longValue());
    }

    public void update(Location location) {
        jdbcTemplate.update("update locations set loc_name = ?, lat = ?, lon = ? where id = ?",
                location.getName(), location.getLat(), location.getLon(), location.getId());
    }

    public void deleteById(long id) {
        jdbcTemplate.update("delete from locations where id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from locations");
    }

    private static Location mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("loc_name");
        double lat = resultSet.getDouble("lat");
        double lon = resultSet.getDouble("lon");

        return new Location(id, name, lat, lon);
    }
}
