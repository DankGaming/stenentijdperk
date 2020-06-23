package hsleiden.stenentijdperk.stenentijdperk.Helpers.Enums;

public enum Middel {
    VOEDSEL(2), HOUT(3), LEEM(4), STEEN(5), GOUD(6);

    private int waarde;

    private Middel(int waarde) {
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }
}
