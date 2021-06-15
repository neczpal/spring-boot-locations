package io.neczpal.locations_spring.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private static final double minLat = -90.0d;
    private static final double maxLat = +90.0d;
    private static final double minLon = -180.0d;
    private static final double maxLon = +180.0d;

    private Type type = Type.LAT;

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return (type == Type.LAT && value >= minLat && value <= maxLat) ||
                (type == Type.LON && value >= minLon && value <= maxLon);
    }
}
