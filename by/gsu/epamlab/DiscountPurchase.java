package by.gsu.epamlab;

public class DiscountPurchase extends AbstractPurchase {
    private Byn discount;

    public DiscountPurchase(Product product,int numberOfPurchaseUnits, Byn discount) {
	super(product,numberOfPurchaseUnits);
	this.discount = discount;
    }

    public DiscountPurchase() {
    }

    public Byn getDiscount() {
	return discount;
    }

    public void setDiscount(Byn discount) {
	this.discount = discount;
    }

    @Override
    protected Byn fieldsToGetCost(Byn price) {
	return price.sub(discount).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    public String fieldsToString() {
	return getNumberOfPurchaseUnits() + ";" + discount;
    }

   
}
