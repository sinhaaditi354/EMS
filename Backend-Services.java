# BACKEND: Spring Boot Service Layer

## AuthService.java
```java
package com.event.service;

import com.event.entity.User;
import com.event.entity.User.UserRole;
import com.event.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User register(String name, String email, String password, UserRole role) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User(name, email, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }
    
    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        throw new RuntimeException("Invalid credentials");
    }
    
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
```

## VendorService.java
```java
package com.event.service;

import com.event.entity.Vendor;
import com.event.entity.User;
import com.event.repository.VendorRepository;
import com.event.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Vendor addVendor(Integer userId, String category, String phone, String address, String city, String state, String pincode) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setCategory(category);
        vendor.setPhone(phone);
        vendor.setAddress(address);
        vendor.setCity(city);
        vendor.setState(state);
        vendor.setPincode(pincode);
        return vendorRepository.save(vendor);
    }
    
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
    
    public Vendor getVendorByUserId(Integer userId) {
        return vendorRepository.findByUserUserId(userId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }
    
    public Vendor updateVendor(Integer vendorId, String phone, String address, String city, String state, String pincode) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendor.setPhone(phone);
        vendor.setAddress(address);
        vendor.setCity(city);
        vendor.setState(state);
        vendor.setPincode(pincode);
        return vendorRepository.save(vendor);
    }
}
```

## ProductService.java
```java
package com.event.service;

import com.event.entity.Product;
import com.event.entity.Vendor;
import com.event.repository.ProductRepository;
import com.event.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private VendorRepository vendorRepository;
    
    public Product addProduct(Integer vendorId, String name, String description, Double price, String imageUrl) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        Product product = new Product();
        product.setVendor(vendor);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setAvailable(true);
        return productRepository.save(product);
    }
    
    public List<Product> getProductsByVendor(Integer vendorId) {
        return productRepository.findByVendorVendorId(vendorId);
    }
    
    public Product updateProduct(Integer productId, String name, String description, Double price, String imageUrl) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        return productRepository.save(product);
    }
    
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
```

## CartService.java
```java
package com.event.service;

import com.event.entity.*;
import com.event.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Cart getOrCreateCart(Integer userId) {
        return cartRepository.findByUserUserId(userId)
            .orElseGet(() -> {
                Cart cart = new Cart();
                cart.setUser(userRepository.findById(userId).orElseThrow());
                return cartRepository.save(cart);
            });
    }
    
    public CartItem addToCart(Integer userId, Integer productId, Integer quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(product.getPrice() * quantity);
        return cartItemRepository.save(cartItem);
    }
    
    public void removeFromCart(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    
    public List<CartItem> getCartItems(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        return cartItemRepository.findByCartCartId(cart.getCartId());
    }
    
    public void clearCart(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        cartItemRepository.deleteByCartCartId(cart.getCartId());
    }
}
```

## OrderService.java
```java
package com.event.service;

import com.event.entity.*;
import com.event.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartService cartService;
    
    public Order placeOrder(Integer userId, Double totalAmount, String paymentMethod,
                            String deliveryName, String deliveryEmail, String deliveryAddress,
                            String deliveryCity, String deliveryState, String deliveryPincode,
                            String deliveryPhone) {
        Order order = new Order();
        order.setUser(new User());
        order.getUser().setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setPaymentMethod(paymentMethod);
        order.setDeliveryName(deliveryName);
        order.setDeliveryEmail(deliveryEmail);
        order.setDeliveryAddress(deliveryAddress);
        order.setDeliveryCity(deliveryCity);
        order.setDeliveryState(deliveryState);
        order.setDeliveryPincode(deliveryPincode);
        order.setDeliveryPhone(deliveryPhone);
        
        Order savedOrder = orderRepository.save(order);
        
        // Create order status
        OrderStatus status = new OrderStatus();
        status.setOrder(savedOrder);
        status.setStatus("RECEIVED");
        orderStatusRepository.save(status);
        
        // Clear cart
        cartService.clearCart(userId);
        return savedOrder;
    }
    
    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findByUserUserId(userId);
    }
    
    public OrderStatus updateOrderStatus(Integer orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderStatus status = orderStatusRepository.findByOrderOrderId(orderId)
            .orElseThrow(() -> new RuntimeException("Order status not found"));
        status.setStatus(newStatus);
        return orderStatusRepository.save(status);
    }
}
```

## MembershipService.java
```java
package com.event.service;

import com.event.entity.Membership;
import com.event.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;
    
    public Membership addMembership(Integer memberRefId, String memberType, String duration) {
        Membership membership = new Membership();
        membership.setMemberRefId(memberRefId);
        membership.setMemberType(memberType);
        membership.setDuration(duration);
        membership.setStartDate(LocalDateTime.now());
        
        // Calculate end date based on duration
        LocalDateTime endDate = LocalDateTime.now();
        switch(duration) {
            case "6_MONTH":
                endDate = endDate.plusMonths(6);
                break;
            case "1_YEAR":
                endDate = endDate.plusYears(1);
                break;
            case "2_YEAR":
                endDate = endDate.plusYears(2);
                break;
        }
        membership.setEndDate(endDate);
        membership.setStatus("ACTIVE");
        return membershipRepository.save(membership);
    }
    
    public Membership updateMembership(Integer membershipId, String duration) {
        Membership membership = membershipRepository.findById(membershipId)
            .orElseThrow(() -> new RuntimeException("Membership not found"));
        membership.setDuration(duration);
        membership.setStartDate(LocalDateTime.now());
        
        LocalDateTime endDate = LocalDateTime.now();
        switch(duration) {
            case "6_MONTH":
                endDate = endDate.plusMonths(6);
                break;
            case "1_YEAR":
                endDate = endDate.plusYears(1);
                break;
            case "2_YEAR":
                endDate = endDate.plusYears(2);
                break;
        }
        membership.setEndDate(endDate);
        return membershipRepository.save(membership);
    }
}
```
