package by.gsu.epamlab;

public enum Material {
    STEEL("Steel",7850.0),COOPER("Cooper",8500.0);
    private final String NAME;
    private double DENSITY;
    Material(){
	this(null,0.0);
    }
    Material(String name, double density){
	this.NAME = name;
	this.DENSITY = density;
    }
    public String getName() {
	return NAME;
    }
    public double getDensity() {
	return DENSITY;
    }
    @Override
    public String toString() {
	return NAME + ";" + DENSITY;
    }
}
