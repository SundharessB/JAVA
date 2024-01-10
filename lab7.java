// lab 7 Program
// Scenario - Ongoing advancement of Amazon's CRM infra, the goal is to streamline customer data management and optimize data processing.
// Implementing three classes customer,product,order
// Using various data structure for optimizing data storage, retrieval and management

import java.util.ArrayList; // For managing dynamic lists of customers, products and orders
import java.util.HashMap; // Efficient retrieval of products and customers
import java.util.HashSet; // Eliminates duplicates
import java.util.TreeSet; // Sorting


// Creation of customer class

class Customer{

    public int customerId;
    public String customerName;
    public int customerPincode;

    // Constructor
    public Customer(int customerId, String customerName, int customerPincode){

        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPincode = customerPincode;
    }

    // Getting customer id
    public int returncustomerId(){
        return customerId;
    }

    // Getting customer name
    public String returncustomerName(){
        return customerName;
    }

    // Getting pincode
    public int returnPincode(){
        return customerPincode;
    }
}

// Creation of product class

class Product{

    int productId;
    String productName;

    // Constructor
    public Product(int productId, String productName){

      this.productId = productId;
      this.productName = productName;

    }

    // Getting product id
    public int getPid(){
        return productId;
    }

    // Getting product name
    public String getPname(){
        return productName;
    }

}

// Creation of order class

class Order{

    public int orderId;
    public Customer customer; // Represents customer associated with the order
    public ArrayList<Product> products;

    public Order(int orderId, Customer customer){

        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();

    }

    public void addProducts(Product product){
        this.products.add(product);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

}

public class AmazonCRM {

    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private ArrayList<Product> products;
    private HashMap<Integer, Customer> customerMap;
    private HashMap<Integer, Product> productMap;
    private HashMap<Customer, HashSet<Product>>  customerproductMap;
    private TreeSet<Customer> sortedCustomers;


    // Constructor
    public AmazonCRM(){

        customers = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
        customerMap = new HashMap<>();
        productMap = new HashMap<>();
        customerproductMap = new HashMap<>();
        sortedCustomers = new TreeSet<>((c1,c2)-> c1.returncustomerName().compareTo(c2.returncustomerName())); // To maintain ordered collection of customers

    }

    // This method is used to add customer

    public void addCustomer(Customer customer){
        customers.add(customer);
        customerMap.put(customer.returncustomerId(), customer);
        sortedCustomers.add(customer);
    }

    // This method is used to add product

    public void addProduct(Product product){

        products.add(product);
        productMap.put(product.getPid(), product);

    }

    // This method is for placing the order

    public void placeOrder(Order order){
        orders.add(order);
        Customer customer = order.customer;
        HashSet<Product> customerProducts = customerproductMap.getOrDefault(customer,new HashSet<>());
        customerProducts.addAll(order.getProducts());
        customerproductMap.put(customer,customerProducts);
    }


    // This method is for finding the customer by id

    public Customer findCustomerById(Integer customerId){
        return customerMap.get(customerId);
    }

    // This method is for finding the product by id

    public Product findProductById(Integer productId){
        return productMap.get(productId);
    }


    // This method retrives the products for respective customer using HashSet

    public HashSet<Product> getProductsForCustomer(Customer customer){

        return customerproductMap.getOrDefault(customer, new HashSet<>());

    }

    // Sorted Customers using TreeSet Data Structure

    public TreeSet<Customer> getSortedCustomers(){
        return sortedCustomers;
    }

    // Main Method
    public static void main(String[] args) {

        AmazonCRM acrm = new AmazonCRM();

        // Creation of customers, products and orders
       
         Customer customer1 = new Customer(234, "Badri", 625013);
         Customer customer2 = new Customer(235, "Jacob", 625009);

         Product product1 = new Product(777, "Hp 15s");
         Product product2 = new Product(888, "Mac");

         Order order1 = new Order(144, customer1);
        //  Order order2 = new Order(2, customer1);

        order1.addProducts(product2);


        // Adding customers, products, and orders to the CRM

        acrm.addCustomer(customer1);
        acrm.addCustomer(customer2);
        acrm.addProduct(product2);
        acrm.addProduct(product1);
        acrm.placeOrder(order1);

        // CRM Functionality

        Customer retrievedCustomer = acrm.findCustomerById(234);
        if(retrievedCustomer != null){
            
            System.out.println("");
            System.out.println("Enhancing Amazon's CRM Infra");
            System.out.println("----------------------------");
            System.out.println("");

            System.out.println("Retrieved Customer: " + retrievedCustomer.returncustomerName());
        }

        HashSet<Product> prodforCust1 = acrm.getProductsForCustomer(customer1);
        System.out.println("Products for customer 1: ");
        for(Product product: prodforCust1){
            System.out.println(product.getPname());
        }

        TreeSet<Customer> sortedCustomers = acrm.getSortedCustomers();
        System.out.println("Sorted Customers");
        for(Customer sortedCustomer : sortedCustomers){
            System.out.println(sortedCustomer.returncustomerName());
        }

    }

}
