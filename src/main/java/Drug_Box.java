public class Drug_Box {

    private Integer drug_number;
    private Drug drug;
    private Box_Storage box_storage;

    public Drug_Box(){
    }

    public Drug_Box(Integer drug_number, Drug drug, Box_Storage box_storage){
       this.drug_number = drug_number;
       this.drug = drug;
       this.box_storage = box_storage;
    }

    public Integer getDrug_number() {
        return drug_number;
    }

    public void setDrug_number(Integer drug_number) {
        this.drug_number = drug_number;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Box_Storage getBox_storage() {
        return this.box_storage;
    }

    public void setBox_storage(Box_Storage box_storage) {
        this.box_storage = box_storage;
    }
}
