import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Date;

public class Orders implements java.io.Serializable {
    private Customer customer;
    private Integer order_id;
    private Timestamp order_date;
    public String supplier;

    public Orders() {
    }

    public Orders(Customer customer, Integer order_id, Timestamp order_date, String supplier) {
        this.customer = customer;
        this.order_id = order_id;
        this.order_date = order_date;
        this.supplier = supplier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}