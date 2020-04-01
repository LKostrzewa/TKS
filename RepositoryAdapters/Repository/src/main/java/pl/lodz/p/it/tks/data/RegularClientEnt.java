package pl.lodz.p.it.tks.data;

public class RegularClientEnt implements ClientTypeEnt {
    @Override
    public double countDiscount(double base) {
        return (base >= 50) ? base * 0.25 : base * 0.2;
    }

    @Override
    public String toString() {
        return "Regular ";
    }
}
