package other;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

public class MyModelMapper {
    public static void main(String[] args) {
        Name name = new Name("firstname", "lastname");
        Customer customer = new Customer(name);
        Address address = new Address("street", "city");
        ModelMapper modelMapper = new ModelMapper();

        Order order = new Order(customer, address);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        System.out.println(orderDTO);

        orderDTO = new OrderDTO("1", "2", "3", "4");
        order = modelMapper.map(orderDTO, Order.class);
        System.out.println(order);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Order {
        Customer customer;
        Address address;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Customer {
        Name name;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Name {
        String firstName;
        String lastName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Address {
        String street;
        String city;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class OrderDTO {
        String customerFirstName;
        String customerLastName;
        String addressStreet;
        String addressCity;
    }

}
