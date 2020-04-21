package pl.lodz.p.it.tks.data;

public class PremiumClientEnt implements ClientTypeEnt {
    @Override
    public double countDiscount(double base) {
        return 0.3 * base;
    }

    @Override
    public String toString() {
        return "Premium ";
    }
}
