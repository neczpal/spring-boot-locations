package io.neczpal.locations_spring.model;

public class Location {
    public final static Location BUDAPEST = new Location(-1, "Budapest", 42.1, 17.8);
    public final static Location SZEGED = new Location(-1, "Szeged", 37.1, 15.8);
    public final static Location SOPRON = new Location(-1, "Sopron", 46.1, 19.8);


    private long id;
    private String name;
    private double lat, lon;

    public Location(long id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
