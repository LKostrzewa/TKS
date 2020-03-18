package pl.lodz.p.it.tks.data;

public class NormalClientEnt implements ClientTypeEnt {
    @Override
    public double countDiscount(double base) {
        return 0;
    }

    @Override
    public String toString() {
        return "Normal ";
    }

}
