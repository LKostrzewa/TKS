package pl.lodz.p.it.tks.dto;

public class RegularClientDTO implements ClientTypeDTO {
    @Override
    public double countDiscount(double base) {
        return (base >= 50) ? base * 0.25 : base * 0.2;
    }

    @Override
    public String toString() {
        return "Regular ";
    }
}
