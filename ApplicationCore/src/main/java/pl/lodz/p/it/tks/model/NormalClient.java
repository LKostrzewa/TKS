package pl.lodz.p.it.tks.model;

public class NormalClient implements ClientType {
    @Override
    public double countDiscount(double base) {
        return 0;
    }

    @Override
    public String toString() {
        return "Normal ";
    }

}
