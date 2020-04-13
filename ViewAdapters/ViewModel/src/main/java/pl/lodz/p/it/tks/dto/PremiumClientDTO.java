package pl.lodz.p.it.tks.dto;

public class PremiumClientDTO implements ClientTypeDTO {
    @Override
    public double countDiscount(double base) {
        return 0.3 * base;
    }

    @Override
    public String toString() {
        return "Premium ";
    }
}
