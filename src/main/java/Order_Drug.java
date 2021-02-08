public class Order_Drug implements java.io.Serializable {
    private Orders order;
    private Drug_Box drug_box;
    private Integer amount;

    public Order_Drug() {
    }


    public Order_Drug(Orders order, Drug_Box drug_box, Integer amount) {
        this.order = order;
        this.drug_box = drug_box;
        this.amount = amount;
    }

    public Drug_Box getDrug_box() {
        return drug_box;
    }

    public Orders getOrder() {
        return order;
    }

    public void setDrug_box(Drug_Box drug_box) {
        this.drug_box = drug_box;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}