import java.util.HashMap;

public class Discount {
    final private static HashMap<String, Discount> listOfPromoCodes = new HashMap<>();
    final private String voucherCode;
    final double discountCost;
    final boolean isPercentage;
    private int uses;

    public Discount(String voucherCode, double discountCost, boolean isPercentage, int uses) {
        this.voucherCode = voucherCode;
        this.discountCost = discountCost;
        this.isPercentage = isPercentage;
        this.uses = uses;
        listOfPromoCodes.put(voucherCode, this);
    }

    static Discount validate(String voucherCode) {
        Discount discount = listOfPromoCodes.get(voucherCode);
        if (discount != null && discount.uses == 0) {
            listOfPromoCodes.remove(voucherCode);
        }
        return discount;
    }

    void apply() {
        if (this.uses > 0) {
            --this.uses;
        }
        if (this.uses == 0) {
            listOfPromoCodes.remove(this.voucherCode);
        }
    }
}