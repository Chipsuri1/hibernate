import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Integer customer_id;
    private String customer_name;
    private String street;
    private String city;
    private Integer zip_code;
    private Date date_of_birth;
    private Integer service_pin;
    private Set<Orders> orders = new HashSet<>(0);

    public Customer() {
    }

    public Customer(Integer customer_id, String customer_name, String street, String city, Integer zip_code, Date date_of_birth, Integer service_pin) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.street = street;
        this.city = city;
        this.zip_code = zip_code;
        this.service_pin = service_pin;
        this.date_of_birth = date_of_birth;
    }

    public String getCustomerInformation(){
        String info = getCustomer_id() + ", " + getCustomer_name() + ", " + getZip_code() + ", " + getCity() + ", " + getStreet() + ", " + getDate_of_birth() + ", " + getService_pin();

        return info;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZip_code() {
        return zip_code;
    }

    public void setZip_code(Integer zip_code) {
        this.zip_code = zip_code;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getService_pin() {
        return service_pin;
    }

    public void setService_pin(Integer service_pin) {
        this.service_pin = service_pin;
    }

    public Set<Orders> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}