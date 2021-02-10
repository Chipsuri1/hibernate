public class Drug {

    private String drug_name;
    private Integer quantity;
    private Float price;

    public Drug(){
    }

    public Drug(String drug_name, Integer quantity, Float price){
        this.drug_name = drug_name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
