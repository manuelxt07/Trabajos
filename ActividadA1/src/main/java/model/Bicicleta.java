package model;

public class Bicicleta {

    private String marca;
    private String color;
    private String serial;
    private int anioAmbiguedad;
    private TipoBicicleta tipoBicicleta;
    private Taller ownedByTaller;

    public Bicicleta(String marca, String color, String serial, int anioAmbiguedad, TipoBicicleta tipoBicicleta, Taller ownedByTaller) {
        this.marca = marca;
        this.color = color;
        this.serial = serial;
        this.anioAmbiguedad = anioAmbiguedad;
        this.tipoBicicleta = tipoBicicleta;
        this.ownedByTaller = ownedByTaller;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getAnioAmbiguedad() {
        return anioAmbiguedad;
    }

    public void setAnioAmbiguedad(int anioAmbiguedad) {
        this.anioAmbiguedad = anioAmbiguedad;
    }

    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    public TipoBicicleta getTipoBicicleta() {
        return tipoBicicleta;
    }

    public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
        this.tipoBicicleta = tipoBicicleta;
    }

    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", serial='" + serial + '\'' +
                ", anioAmbiguedad=" + anioAmbiguedad +
                ", tipoBicicleta=" + tipoBicicleta +
                ", ownedByTaller=" + ownedByTaller +
                '}';
    }
}
