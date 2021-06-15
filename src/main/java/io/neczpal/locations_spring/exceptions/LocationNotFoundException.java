package io.neczpal.locations_spring.exceptions;

public class LocationNotFoundException extends IllegalArgumentException{

    public LocationNotFoundException(String s) {
        super(s);
    }
}
