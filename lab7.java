
import java.util.ArrayList; // For managing dynamic lists of customers, products and orders
import java.util.HashMap; // Efficient retrieval of products and customers
import java.util.HashSet; // Eliminates duplicates
import java.util.TreeSet; // Sorting



class Customer{

    public int customerId;
    public String customerName;
    public int customerPincode;

    public Customer(int customerId, String customerName, int customerPincode){

        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPincode = customerPincode;
    }


    public int returncustomerId(){
        return customerId;
    }

   
    public String returncustomerName(){
        return customerName;
    }

   
    public int returnPincode(){
        return customerPincode;
    }
}



class Product{

    int productId;
    String productName;

    
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



    public void addCustomer(Customer customer){
        customers.add(customer);
        customerMap.put(customer.returncustomerId(), customer);
        sortedCustomers.add(customer);
    }

  
    public void addProduct(Product product){

        products.add(product);
        productMap.put(product.getPid(), product);

    }

   

    public void placeOrder(Order order){
        orders.add(order);
        Customer customer = order.customer;
        HashSet<Product> customerProducts = customerproductMap.getOrDefault(customer,new HashSet<>());
        customerProducts.addAll(order.getProducts());
        customerproductMap.put(customer,customerProducts);
    }


 

    public Customer findCustomerById(Integer customerId){
        return customerMap.get(customerId);
    }

    // This method is for finding the product by id

    public Product findProductById(Integer productId){
        return productMap.get(productId);
    }


    
    public HashSet<Product> getProductsForCustomer(Customer customer){

        return customerproductMap.getOrDefault(customer, new HashSet<>());

    }


    public TreeSet<Customer> getSortedCustomers(){
        return sortedCustomers;
    }

    
    public static void main(String[] args) {

        AmazonCRM acrm = new AmazonCRM();

       
         Customer customer1 = new Customer(234, "Badri", 625013);
         Customer customer2 = new Customer(235, "Jacob", 625009);

         Product product1 = new Product(777, "Hp 15s");
         Product product2 = new Product(888, "Mac");

         Order order1 = new Order(144, customer1);

        order1.addProducts(product2);



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
