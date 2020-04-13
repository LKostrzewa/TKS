package pl.lodz.p.it.tks.dto;

public class NormalClientDTO implements ClientTypeDTO {
    @Override
    public double countDiscount(double base) {
        return 0;
    }

    @Override
    public String toString() {
        return "Normal ";
    }

}
