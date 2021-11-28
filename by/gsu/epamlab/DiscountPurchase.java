package by.gsu.epamlab;

public class DiscountPurchase extends AbstractPurchase {
    private Byn discount;

    public DiscountPurchase(int numberOfPurchaseUnits, Byn discount) {
	super( numberOfPurchaseUnits);
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
    protected Byn fieldsToGetCost() {
	return Product.PRICE.sub(discount).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    protected String fieldsToString() {
	return discount.toString();
    }

   
}
