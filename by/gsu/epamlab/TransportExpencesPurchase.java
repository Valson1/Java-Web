package by.gsu.epamlab;

public class TransportExpencesPurchase extends AbstractPurchase {
    private Byn transportExpences;

    public TransportExpencesPurchase(Product product, int numberOfPurchaseUnits, Byn transportExpences) {
	super(product, numberOfPurchaseUnits);
	this.transportExpences = transportExpences;
    }

    public TransportExpencesPurchase() {
	super();
    }

    public Byn getTransportExpences() {
	return transportExpences;
    }

    public void setTransportExpences(Byn transportExpences) {
	this.transportExpences = transportExpences;
    }

    @Override
    protected Byn fieldsToGetCost(Byn price) {
	return price.multiply(getNumberOfPurchaseUnits()).sum(transportExpences);
    }

    @Override
    public String fieldsToString() {
	return getNumberOfPurchaseUnits() + ";" + transportExpences;
    }

}
