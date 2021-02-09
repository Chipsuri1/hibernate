import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleApplication {

    private Scanner scanner;
    private SessionFactory sessionFactory;
    private Session session;

    public void start() throws ParseException {
        startSession();
        getInput();
        endSession();
    }

    private void startSession() {
        sessionFactory = HibernateUtility.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        scanner = new Scanner(System.in);
    }

    private void getInput() throws ParseException {
        boolean finished = false;

        while (!finished) {
            System.out.println("Please insert your Query-Number or insert 'end' to finish:");
            String query = scanner.nextLine();

            String input = getInputString(query);


            switch (input) {
                case "add drug to box":
                    String[] drugInfo = query.substring(query.indexOf('(') + 1, query.indexOf(')')).split(",");
                    String box_id = query.substring(query.indexOf("x") + 3, query.indexOf("x") + 6);

                    addDrugToBox(Integer.valueOf(drugInfo[0]), drugInfo[1], Float.valueOf(drugInfo[2]), Integer.valueOf(drugInfo[3]), box_id);
                    break;
                case "remove drug by number":
                    String drug_number = query.substring(query.indexOf('(') + 1, query.indexOf(')'));

                    removeDrugByNumber(Integer.valueOf(drug_number));
                    break;
                case "remove drug by name":
                    String drug_name = query.substring(query.indexOf('(') + 1, query.indexOf(')'));

                    removeDrugByName(drug_name);
                    break;
                case "add customer":
                    String[] customer = query.substring(query.indexOf('(') + 1, query.indexOf(')')).split(",");

                    addCustomer(Integer.valueOf(customer[0]), customer[1], customer[2], Integer.valueOf(customer[3]), customer[4], new SimpleDateFormat("dd.MM.yyyy").parse(customer[5]), Integer.valueOf(customer[6]));
                    break;
                case "search customer by name":
                    String customer_name = query.substring(query.indexOf('(') + 1, query.indexOf(')'));

                    searchCustomerByName(customer_name);
                    break;
                case "create new order":
                    String order_id = query.substring(query.indexOf("order") + 7, query.indexOf(')'));
                    String customer_id = query.substring(query.indexOf("customer") + 10);

                    createNewOrderForCustomer(Integer.valueOf(order_id), Integer.valueOf(customer_id.substring(0, customer_id.length() - 1)));
                    break;
                case "add drug to order":
                    String drug_id = query.substring(query.indexOf("(") + 1, query.indexOf(')'));
                    String quantity = query.substring(query.indexOf("quantity") +9, query.indexOf("to") - 1);
                    String order_Id = query.substring(query.indexOf("order") + 7);

                    addDrugWithQuantityToOrder(Integer.valueOf(drug_id), Integer.valueOf(quantity), Integer.valueOf(order_Id.substring(0, order_Id.length() - 1)));
                    break;
                case "send order":
                    String id = query.substring(query.indexOf('(') + 1, query.indexOf(')'));
                    String supplier = query.substring(query.indexOf('y') + 3);
                    supplier = supplier.substring(0, supplier.length() - 1);

                    sendOrderBy(Integer.valueOf(id), supplier);
                    break;
                case "end":
                    finished = true;
                    System.out.println("Finished");
                    break;
                default:
                    System.out.println("Input invalid! Please check your Query Number.");
                    break;
            }
        }

    }

    private void endSession() {
        session.getTransaction().commit();
    }

    private String getInputString(String query) {
        if (query.equals("end")) {
            return "end";
        } else if (query.indexOf("to order") != -1) {
            return "add drug to order";
        } else if (query.indexOf("to box") != -1) {
            return "add drug to box";
        } else {
            return query.substring(0, query.indexOf('(') - 1);
        }
    }

    private void addDrugToBox(Integer drug_number, String drug_name, Float price, Integer quantity, String box_id) {
        Drug drug = new Drug(drug_name, quantity, price);
        Box_Storage box_storage = new Box_Storage();
        box_storage.setBox_id(box_id);

        Drug_Box drug_box = new Drug_Box(drug_number, drug, box_storage);

        session.save(drug_box);
    }

    private void removeDrugByNumber(Integer drug_number) {
        Query query = session.createQuery("from Drug_Box Dr where drug_number = :drugnumber");
        query.setParameter("drugnumber", drug_number);

        Drug_Box drug_box = (Drug_Box) query.list().get(0);
        Drug drug = drug_box.getDrug();

        session.delete(drug_box);
        session.delete(drug);
    }

    private void removeDrugByName(String drug_name) {
        Drug drug = new Drug();
        drug.setDrug_name(drug_name);

        session.delete(drug);
    }

    private void addCustomer(Integer customer_id, String customer_name, String street, Integer zip_code, String city, Date date_of_birth, Integer service_pin) {
        Customer customer = new Customer(customer_id, customer_name, street, city, zip_code, date_of_birth, service_pin);

        session.save(customer);
    }

    private void searchCustomerByName(String name) {
        Query query = session.createQuery("from Customer C where customer_name = :nameParameter");
        query.setParameter("nameParameter", name);

        Customer customer = (Customer) query.list().get(0);
        System.out.println("Customer Information: " + customer.getCustomerInformation());
    }

    private void createNewOrderForCustomer(Integer order_id, Integer customer_id) {
        Query query = session.createQuery("from Customer C where customer_id = :customer_idParameter");
        query.setParameter("customer_idParameter", customer_id);

        Customer customer = (Customer) query.list().get(0);

        Orders order = new Orders();
        order.setOrder_date(new Timestamp(System.currentTimeMillis()));
        order.setOrder_id(order_id);
        order.setCustomer(customer);

        session.save(order);
    }

    private void addDrugWithQuantityToOrder(Integer drug_number, Integer quantity, Integer order_id) {
        Query queryDrugBox = session.createQuery("from Drug_Box D where drug_number = :drugNumber");
        queryDrugBox.setParameter("drugNumber", drug_number);
        Drug_Box drug_box = (Drug_Box) queryDrugBox.list().get(0);

        Query queryOrder = session.createQuery("from Orders D where order_id = :orderID");
        queryOrder.setParameter("orderID", order_id);
        Orders order = (Orders) queryOrder.list().get(0);

        Order_Drug order_drug = new Order_Drug(order, drug_box, quantity);

        session.save(order_drug);
    }

    private void sendOrderBy(Integer order_id, String shipping_company) {
        Query query = session.createQuery("UPDATE Orders SET supplier = :supplier where order_id = :order_id");

        query.setParameter("supplier", shipping_company);
        query.setParameter("order_id", order_id);

        query.executeUpdate();
        System.out.println("Update succesful");
    }

}
