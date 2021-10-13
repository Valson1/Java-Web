package by.gsu.epamlab;

public class Material {
    private String name;
    private double density;
    public Material() {
	super();
    }

    public Material(String name,double density) {
	super();
	this.name = name;
	this.density = density;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
	    this.density = density;	    
    }

    @Override
    public String toString() {
	return name + ";" + density;
    }
    
}
