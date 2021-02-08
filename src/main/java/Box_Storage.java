public class Box_Storage {

    private String box_id;
    private String storage_id;

    public Box_Storage(){

    }

    public Box_Storage(String box_id, String storage_id){
        this.box_id = box_id;
        this.storage_id = storage_id;
    }

    public String getBox_id() {
        return box_id;
    }

    public void setBox_id(String box_id) {
        this.box_id = box_id;
    }

    public String getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(String storage_id) {
        this.storage_id = storage_id;
    }
}
