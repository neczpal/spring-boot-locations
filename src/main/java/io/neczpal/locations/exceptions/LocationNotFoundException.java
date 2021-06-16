package io.neczpal.locations.exceptions;

public class LocationNotFoundException extends IllegalArgumentException{

    public LocationNotFoundException(String s) {
        super(s);
    }
}
