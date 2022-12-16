package task6;

public class House {
    private int id;
    private int apartmentNumber;
    private double area;
    private int floor;
    private int numRooms;
    private String street;
    private String buildingType;
    private int lifetime;

    // default constructor
    public House() {
        this.id = 0;
        this.apartmentNumber = 0;
        this.area = 0.0;
        this.floor = 0;
        this.numRooms = 0;
        this.street = "";
        this.buildingType = "";
        this.lifetime = 0;
    }

    // constructor with parameters
    public House(int id, int apartmentNumber, double area, int floor, int numRooms, String street, String buildingType, int lifetime) {
        this.id = id;
        this.apartmentNumber = apartmentNumber;
        this.area = area;
        this.floor = floor;
        this.numRooms = numRooms;
        this.street = street;
        this.buildingType = buildingType;
        this.lifetime = lifetime;
    }

    // setter and getter methods for each attribute
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public static House[] createHouses() {
        Object[][] data = {{1, 101, 1000.0, 1, 2, "task6.Main St", "apartment", 20},
                {2, 102, 1200.0, 2, 3, "task6.Main St", "apartment", 20},
                {3, 103, 1500.0, 3, 3, "task6.Main St", "apartment", 20},
                {4, 201, 1200.0, 4, 2, "Second Ave", "house", 30},
                {5, 202, 1600.0, 5, 4, "Second Ave", "house", 30}};
        House[] houses = new House[data.length];
        for (int i = 0; i < data.length; i++) {
            int id = (int) data[i][0];
            int apartmentNumber = (int) data[i][1];
            double area = (double) data[i][2];
            int floor = (int) data[i][3];
            int numRooms = (int) data[i][4];
            String street = (String) data[i][5];
            String buildingType = (String) data[i][6];
            int lifetime = (int) data[i][7];
            houses[i] = new House(id, apartmentNumber, area, floor, numRooms, street, buildingType, lifetime);
        }
        return houses;
    }





    // toString method
    public String toString() {
        return "task6.House [id=" + id + ", apartmentNumber=" + apartmentNumber +
                ", area=" + area + ", floor=" + floor + ", numRooms=" + numRooms +
                ", street=" + street + ", buildingType=" + buildingType + ", lifetime=" + lifetime + "]";
    }
}

