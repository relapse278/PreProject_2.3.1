package web.model;

public class Car {
    private final String brand;
    private final String model;
    private final int manufactureYear;

    public Car(String brand, String model, int manufactureYear) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }
}
