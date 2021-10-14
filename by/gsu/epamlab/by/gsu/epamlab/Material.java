package by.gsu.epamlab;

public class Material {
    private final String NAME;
    private final double DENSITY;
    public Material() {
	super();
	this.NAME = "";
	this.DENSITY = 0;
    }

    public Material(String name,double density) {
	super();
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
