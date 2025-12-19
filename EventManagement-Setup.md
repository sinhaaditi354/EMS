# Event Management ERP Portal - Full Stack Implementation

## Project Setup Guide

### Tech Stack
- **Backend**: Java 11+, Spring Boot 2.7+, Hibernate/JPA
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Database**: MySQL 8.0+
- **Server**: Apache Tomcat (embedded in Spring Boot)

### Database Schema Setup

```sql
-- Create Database
CREATE DATABASE event_management;
USE event_management;

-- Users Table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER', 'VENDOR') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Vendors Table
CREATE TABLE vendors (
    vendor_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    category ENUM('CATERING', 'FLORIST', 'DECORATION', 'LIGHTING', 'PHOTOGRAPHY', 'MUSIC') NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Memberships Table
CREATE TABLE memberships (
    membership_id INT PRIMARY KEY AUTO_INCREMENT,
    member_type ENUM('USER', 'VENDOR') NOT NULL,
    member_ref_id INT NOT NULL,
    duration ENUM('6_MONTH', '1_YEAR', '2_YEAR') DEFAULT '6_MONTH',
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP,
    status ENUM('ACTIVE', 'EXPIRED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products Table
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    vendor_id INT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255),
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id) ON DELETE CASCADE
);

-- Cart Table
CREATE TABLE cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_user_cart (user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Cart Items Table
CREATE TABLE cart_items (
    cart_item_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT DEFAULT 1,
    total_price DECIMAL(12, 2),
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- Orders Table
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    total_amount DECIMAL(12, 2) NOT NULL,
    payment_method ENUM('CASH', 'UPI') NOT NULL,
    delivery_name VARCHAR(100),
    delivery_email VARCHAR(100),
    delivery_address VARCHAR(255),
    delivery_city VARCHAR(50),
    delivery_state VARCHAR(50),
    delivery_pincode VARCHAR(10),
    delivery_phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Order Items Table
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT DEFAULT 1,
    unit_price DECIMAL(10, 2),
    total_price DECIMAL(12, 2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Order Status Table
CREATE TABLE order_status (
    order_status_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    status ENUM('RECEIVED', 'READY_FOR_SHIPPING', 'OUT_FOR_DELIVERY', 'DELIVERED', 'CANCELLED') DEFAULT 'RECEIVED',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

-- User Requests Table
CREATE TABLE user_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    description TEXT,
    status ENUM('OPEN', 'IN_PROGRESS', 'CLOSED') DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Insert Test Data
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@event.com', 'admin123', 'ADMIN'),
('John Vendor', 'john@vendor.com', 'vendor123', 'VENDOR'),
('Jane User', 'jane@user.com', 'user123', 'USER');
```

### Backend Folder Structure
```
event-management-backend/
├── src/
│   └── main/
│       ├── java/com/event/
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── entity/
│       │   ├── dto/
│       │   ├── config/
│       │   └── EventManagementApp.java
│       └── resources/
│           ├── application.properties
│           └── static/
│               ├── css/
│               ├── js/
│               └── pages/
└── pom.xml
```

### Frontend Folder Structure
```
event-management-frontend/
├── index.html
├── css/
│   ├── style.css
│   └── responsive.css
├── js/
│   ├── auth.js
│   ├── vendor.js
│   ├── user.js
│   ├── admin.js
│   └── api.js
└── pages/
    ├── login.html
    ├── vendor-dashboard.html
    ├── user-dashboard.html
    ├── admin-dashboard.html
    └── (other pages)
```

### Installation Steps

1. **Create MySQL Database**
   ```bash
   mysql -u root -p < database.sql
   ```

2. **Configure Backend** (application.properties)
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/event_management
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   server.port=8080
   ```

3. **Run Backend**
   ```bash
   mvn spring-boot:run
   ```

4. **Serve Frontend**
   - Use Live Server or Apache
   - Access: `http://localhost:8080`

### API Endpoints Overview

**Auth APIs:**
- POST `/api/auth/login` - User login
- POST `/api/auth/logout` - User logout
- POST `/api/auth/register` - User/Vendor registration

**Admin APIs:**
- GET `/api/admin/users` - List all users
- POST `/api/admin/users` - Add user
- PUT `/api/admin/users/{id}` - Update user
- DELETE `/api/admin/users/{id}` - Delete user
- GET `/api/admin/vendors` - List vendors
- POST `/api/admin/memberships` - Add membership

**Vendor APIs:**
- GET `/api/vendor/products` - List vendor's products
- POST `/api/vendor/products` - Add product
- PUT `/api/vendor/products/{id}` - Update product
- DELETE `/api/vendor/products/{id}` - Delete product
- GET `/api/vendor/orders` - List vendor's orders
- PUT `/api/vendor/orders/{id}/status` - Update order status

**User APIs:**
- GET `/api/user/vendors` - List all vendors
- GET `/api/user/products/{vendorId}` - List vendor products
- GET `/api/user/cart` - Get cart items
- POST `/api/user/cart/items` - Add to cart
- DELETE `/api/user/cart/items/{id}` - Remove from cart
- POST `/api/user/orders` - Place order
- GET `/api/user/orders` - List user orders

### Key Features Implemented
✅ Role-based access control (Admin, Vendor, User)
✅ Session management with timeout
✅ Form validations on frontend and backend
✅ Password encryption (bcrypt)
✅ Membership management (6 months, 1 year, 2 years)
✅ Product CRUD operations
✅ Shopping cart functionality
✅ Order placement and tracking
✅ Order status updates
✅ Admin maintenance module
✅ Responsive UI design
✅ Error handling and logging
