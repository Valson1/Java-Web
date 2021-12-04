package by.gsu.epamlab;

public class NumberDiscountPurchase extends AbstractPurchase {
    private Byn discount;

    public NumberDiscountPurchase(Product product,int numberOfPurchaseUnits, Byn discount) {
	super(product,numberOfPurchaseUnits);
	this.discount = discount;
    }

    public NumberDiscountPurchase() {
    }

    public Byn getDiscount() {
	return discount;
    }

    public void setDiscount(Byn discount) {
	this.discount = discount;
    }

    @Override
    protected Byn fieldsToGetCost(Byn naturalCost) {
	return naturalCost.sub(discount.multiply(getNumberOfPurchaseUnits()));
    }

    @Override
    public String fieldsToString() {
	return super.fieldsToString() + ";" + discount;
    }

   
}
