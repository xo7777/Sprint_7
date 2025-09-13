package scooter.pojo;

import java.util.List;

public class OrderCreateJson {
    private String firstName = "Федор";
    private String lastName = "Достоевский";
    private String address = "Москва, ул. Саратовская, д.18, кв.256";
    private int metroStation = 3;
    private String phone = "8 903 856 16 75";
    private String rentTime = "5";
    private String deliveryDate = "2025-09-15";
    private String comment = "Припаркуйте у подъезда";
    private List<String> color;

    public OrderCreateJson (List<String> color) {
        this.color = color;
    }
    public OrderCreateJson () {}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getMetroStation() {
        return metroStation;
    }
    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }



    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRentTime() {
        return rentTime;
    }
    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<String> getColor() {
        return color;
    }
    public void setColor(List<String> color) {
        this.color = color;
    }
}
