package by.gsu.epamlab;

public class TransportExpencesPurchase extends AbstractPurchase{
    private Byn transportExpences;
    
    public TransportExpencesPurchase(int numberOfPurchaseUnits, Byn transportExpences) {
	super(numberOfPurchaseUnits);
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
    protected Byn fieldsToGetCost() {
	return Product.PRICE.sum(transportExpences).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    protected String fieldsToString() {
	return transportExpences.toString();
    }
   
}
