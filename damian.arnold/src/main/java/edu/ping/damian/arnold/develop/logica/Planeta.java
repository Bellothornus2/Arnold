package edu.ping.damian.arnold.develop.logica;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import org.assertj.core.util.Arrays;
public enum Planeta {
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7);

    private final double mass; //en kilos
    private final double radius; //en metros
    //constante universal gravitacional 
    private static final double G = 6.67300E-11;

    private Planeta(double mass, double radius){
        this.mass = mass;
        this.radius = radius;
    }
    public double getMasa() {
        return this.mass;
    }
    public double getRadio() {
        return this.radius;
    }
    //tomamos como referencia de planeta rocoso mas grande
    //del sistema solar la tierra, calculamos su densidad
    //y así discriminamos a los rocosos y terrestres
    public static List<Planeta> getPlanetasTerrestres(){
        return Stream.of(Planeta.values()).filter(
            p -> p.getMasa() / p.getRadio() <= 9.3696e17
        ).collect(Collectors.toList());
    }
    public static List<Planeta> getGigantesGaseosos(){
        return Stream.of(Planeta.values()).filter(
            p -> p.getMasa() / p.getRadio() >= 9.3696e17
        ).collect(Collectors.toList());
    }
    private double surfaceGravity() {
        return G * this.getMasa() / (this.getRadio() * this.getRadio());
    }
    public double pesoSuperficie(double yourWeight){
        return Planeta.yourMassEarth(yourWeight) * this.surfaceGravity();
    }
    private static double yourMassEarth(double yourWeight){
        return yourWeight / Planeta.EARTH.surfaceGravity();
    }
}
