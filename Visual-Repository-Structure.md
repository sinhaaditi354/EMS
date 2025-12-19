# Event Management ERP - Visual Repository Structure

## 🏗️ Complete Project Architecture Diagram

```
╔══════════════════════════════════════════════════════════════════════════════╗
║           EVENT MANAGEMENT ERP - FULL STACK APPLICATION                    ║
║                    GitHub Repository: Root Structure                        ║
╚══════════════════════════════════════════════════════════════════════════════╝

event-management-erp/ (ROOT)
│
├─── 📄 README.md                          ← START HERE: Project Overview
├─── 📄 LICENSE                            ← MIT License
├─── 📄 .gitignore                         ← Git ignore rules
├─── 📄 CONTRIBUTING.md                    ← Contribution guide
├─── 📄 CHANGELOG.md                       ← Version history
│
│
╔═══════════════════════════════════════════════════════════════════════════╗
║ TIER 1: BACKEND (Java Spring Boot REST APIs)                              ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── backend/                                                              │
│    │                                                                      │
│    ├─── 📄 pom.xml                       ← Maven Dependencies            │
│    ├─── 📄 mvnw & mvnw.cmd              ← Maven Wrappers               │
│    ├─── 📄 README.md                     ← Backend Setup Guide          │
│    │                                                                      │
│    ├─── src/main/java/com/event/                                        │
│    │    │                                                                 │
│    │    ├─── 📄 EventManagementApp.java  ← Main Entry Point (1 file)   │
│    │    │                                                                 │
│    │    ├─── controller/                 ← REST Endpoints (4 files)    │
│    │    │    ├─── AuthController.java                                   │
│    │    │    ├─── AdminController.java                                  │
│    │    │    ├─── VendorController.java                                 │
│    │    │    └─── UserController.java                                   │
│    │    │                                                                 │
│    │    ├─── service/                    ← Business Logic (6 files)    │
│    │    │    ├─── AuthService.java                                      │
│    │    │    ├─── VendorService.java                                    │
│    │    │    ├─── ProductService.java                                   │
│    │    │    ├─── CartService.java                                      │
│    │    │    ├─── OrderService.java                                     │
│    │    │    └─── MembershipService.java                                │
│    │    │                                                                 │
│    │    ├─── repository/                 ← Data Access (10 files)      │
│    │    │    ├─── UserRepository.java                                   │
│    │    │    ├─── VendorRepository.java                                 │
│    │    │    ├─── ProductRepository.java                                │
│    │    │    ├─── CartRepository.java                                   │
│    │    │    ├─── CartItemRepository.java                               │
│    │    │    ├─── OrderRepository.java                                  │
│    │    │    ├─── OrderItemRepository.java                              │
│    │    │    ├─── OrderStatusRepository.java                            │
│    │    │    ├─── MembershipRepository.java                             │
│    │    │    └─── UserRequestRepository.java                            │
│    │    │                                                                 │
│    │    ├─── entity/                     ← Database Models (10 files)  │
│    │    │    ├─── User.java                                             │
│    │    │    ├─── Vendor.java                                           │
│    │    │    ├─── Product.java                                          │
│    │    │    ├─── Cart.java                                             │
│    │    │    ├─── CartItem.java                                         │
│    │    │    ├─── Order.java                                            │
│    │    │    ├─── OrderItem.java                                        │
│    │    │    ├─── OrderStatus.java                                      │
│    │    │    ├─── Membership.java                                       │
│    │    │    └─── UserRequest.java                                      │
│    │    │                                                                 │
│    │    ├─── dto/                        ← Data Transfer Objects (3)   │
│    │    │    ├─── LoginRequest.java                                     │
│    │    │    ├─── SignupRequest.java                                    │
│    │    │    └─── ApiResponse.java                                      │
│    │    │                                                                 │
│    │    ├─── config/                     ← Configuration (1 file)      │
│    │    │    └─── SecurityConfig.java                                   │
│    │    │                                                                 │
│    │    └─── exception/                  ← Exception Handler (1 file)  │
│    │         └─── GlobalExceptionHandler.java                           │
│    │                                                                      │
│    ├─── src/main/resources/                                              │
│    │    ├─── 📄 application.properties    ← Spring Config               │
│    │    └─── 📄 application.yml           ← YAML Config                 │
│    │                                                                      │
│    ├─── src/test/java/                    ← Unit Tests                   │
│    │                                                                      │
│    └─── docs/                             ← Backend Docs (4 files)     │
│         ├─── API_ENDPOINTS.md                                            │
│         ├─── DATABASE_SCHEMA.md                                          │
│         ├─── SETUP_GUIDE.md                                              │
│         └─── DEPLOYMENT.md                                               │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝


╔═══════════════════════════════════════════════════════════════════════════╗
║ TIER 2: FRONTEND (Single Page Application - SPA)                          ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── frontend/                                                             │
│    │                                                                      │
│    ├─── 📄 index.html                    ← Main SPA (1 file)           │
│    ├─── 📄 README.md                     ← Frontend Setup Guide        │
│    │                                                                      │
│    ├─── css/                             ← Stylesheets (3 files)      │
│    │    ├─── style.css                                                  │
│    │    ├─── responsive.css                                             │
│    │    └─── variables.css                                              │
│    │                                                                      │
│    ├─── js/                              ← JavaScript Logic (6 files)  │
│    │    ├─── api.js                      ← API Service Layer          │
│    │    ├─── main.js                     ← Core Logic                 │
│    │    ├─── auth.js                     ← Auth Functions             │
│    │    ├─── admin.js                    ← Admin Functions            │
│    │    ├─── vendor.js                   ← Vendor Functions           │
│    │    └─── user.js                     ← User Functions             │
│    │                                                                      │
│    ├─── assets/                                                          │
│    │    ├─── images/                     ← Images (logo, banners)      │
│    │    └─── icons/                      ← SVG Icons                    │
│    │                                                                      │
│    ├─── pages/                           ← HTML Templates (optional)   │
│    │    ├─── login.html                                                 │
│    │    ├─── admin-dashboard.html                                       │
│    │    ├─── vendor-dashboard.html                                      │
│    │    └─── user-dashboard.html                                        │
│    │                                                                      │
│    └─── docs/                            ← Frontend Docs (3 files)    │
│         ├─── SETUP.md                                                   │
│         ├─── API_INTEGRATION.md                                         │
│         └─── DEPLOYMENT.md                                              │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝


╔═══════════════════════════════════════════════════════════════════════════╗
║ TIER 3: DATABASE (MySQL Schema & Data)                                    ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── database/                                                             │
│    │                                                                      │
│    ├─── 📄 schema.sql                    ← Complete Schema (1 file)    │
│    ├─── 📄 seed_data.sql                 ← Test Data (1 file)          │
│    ├─── 📄 README.md                     ← Database Docs               │
│    │                                                                      │
│    └─── migrations/                      ← Version Migrations (3 files)│
│         ├─── v1.0.0_initial_schema.sql                                  │
│         ├─── v1.1.0_add_features.sql                                    │
│         └─── v1.2.0_optimize.sql                                        │
│                                                                           │
│    TABLES CREATED:                                                       │
│    ├─ Users                              (User management)               │
│    ├─ Vendors                            (Vendor profiles)               │
│    ├─ Products                           (Product listings)              │
│    ├─ Cart                               (Shopping cart)                 │
│    ├─ CartItems                          (Cart items)                    │
│    ├─ Orders                             (Order management)              │
│    ├─ OrderItems                         (Order line items)              │
│    ├─ OrderStatus                        (Order tracking)                │
│    ├─ Memberships                        (Membership plans)              │
│    └─ UserRequests                       (Customer requests)             │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝


╔═══════════════════════════════════════════════════════════════════════════╗
║ DOCUMENTATION & GUIDES                                                     ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── docs/                                                                 │
│    │                                                                      │
│    ├─── 📄 ARCHITECTURE.md                ← System Design               │
│    ├─── 📄 API_REFERENCE.md               ← Complete API Docs           │
│    ├─── 📄 DATABASE_DESIGN.md             ← ER Diagrams                │
│    ├─── 📄 SETUP_GUIDE.md                 ← Installation Steps          │
│    ├─── 📄 DEPLOYMENT.md                  ← Production Deployment       │
│    ├─── 📄 SECURITY.md                    ← Security Practices          │
│    ├─── 📄 TESTING.md                     ← Testing Guidelines          │
│    ├─── 📄 TROUBLESHOOTING.md             ← Common Issues               │
│    │                                                                      │
│    └─── diagrams/                         ← Visual Diagrams             │
│         ├─── system-architecture.png                                     │
│         ├─── database-er-diagram.png                                     │
│         ├─── user-flow.png                                               │
│         └─── deployment-diagram.png                                      │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝


╔═══════════════════════════════════════════════════════════════════════════╗
║ DEVOPS & INFRASTRUCTURE                                                    ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── scripts/                              ← Build & Deploy               │
│    ├─── setup.sh                         ← Initial setup                │
│    ├─── build.sh                         ← Build script                 │
│    ├─── deploy.sh                        ← Deployment script            │
│    ├─── test.sh                          ← Test execution               │
│    └─── docker-compose.yml               ← Docker compose               │
│                                                                           │
├─── docker/                               ← Container Configuration      │
│    ├─── Dockerfile.backend               ← Backend image                │
│    ├─── Dockerfile.frontend              ← Frontend image               │
│    ├─── Dockerfile.db                    ← Database image               │
│    └─── docker-compose.yml               ← Multi-container setup        │
│                                                                           │
├─── config/                               ← Configuration Templates      │
│    ├─── .env.example                     ← Env template                 │
│    ├─── .env.development                 ← Dev config                   │
│    ├─── .env.production                  ← Prod config                  │
│    └─── nginx.conf                       ← Nginx config                 │
│                                                                           │
└─── .github/                              ← GitHub Actions CI/CD         │
     └─── workflows/                                                       │
          ├─── ci-backend.yml              ← Backend CI                   │
          ├─── ci-frontend.yml             ← Frontend CI                  │
          └─── deploy.yml                  ← Auto deployment              │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝


╔═══════════════════════════════════════════════════════════════════════════╗
║ TESTING & QUALITY ASSURANCE                                               ║
╠═══════════════════════════════════════════════════════════════════════════╣
│                                                                           │
├─── tests/                                                                │
│    ├─── 📄 integration-tests.sh           ← End-to-end tests            │
│    ├─── 📄 api-tests.postman_collection   ← API testing                 │
│    │                                                                      │
│    └─── frontend-tests/                                                  │
│         ├─── test-cases.md                ← Manual test cases           │
│         └─── automation/                  ← Selenium tests (optional)   │
│                                                                           │
│    backend/src/test/java/                ← Unit Tests                   │
│    ├─── controller/                       ← Controller tests            │
│    ├─── service/                          ← Service tests               │
│    └─── repository/                       ← Repository tests            │
│                                                                           │
╚═══════════════════════════════════════════════════════════════════════════╝
```

---

## 📈 File Count Summary

```
┌─────────────────────┬───────┬────────────────────┐
│ Component           │ Files │ Purpose            │
├─────────────────────┼───────┼────────────────────┤
│ Backend Controllers │   4   │ REST Endpoints     │
│ Backend Services    │   6   │ Business Logic     │
│ Backend Repositories│  10   │ Database Access    │
│ Backend Entities    │  10   │ Data Models        │
│ Backend DTOs        │   3   │ Data Transfer      │
│ Backend Config      │   2   │ Configuration      │
│ Backend Tests       │   8   │ Unit Tests         │
├─────────────────────┼───────┼────────────────────┤
│ Frontend HTML/CSS   │   5   │ UI & Styling       │
│ Frontend JavaScript │   6   │ Logic & APIs       │
│ Frontend Assets     │   4   │ Images & Icons     │
│ Frontend Docs       │   3   │ Documentation      │
├─────────────────────┼───────┼────────────────────┤
│ Database Schema     │   5   │ Database Setup     │
│ Documentation       │  10   │ Project Docs       │
│ Scripts & Config    │  12   │ DevOps & Setup     │
│ Tests               │   8   │ Test Automation    │
├─────────────────────┼───────┼────────────────────┤
│ ROOT PROJECT        │ ~95   │ Complete Project   │
└─────────────────────┴───────┴────────────────────┘
```

---

## 🔗 Data Flow Diagram

```
┌──────────────────────────────────────────────────────────────────┐
│  FRONTEND (Browser)                                              │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │  index.html (SPA)                                         │ │
│  │  ├─ HTML Structure                                        │ │
│  │  ├─ CSS Styling (style.css)                              │ │
│  │  └─ JavaScript (api.js + main.js)                        │ │
│  └────────────────────────────────────────────────────────────┘ │
│         ↓ HTTP Requests (JSON)                                  │
└──────────────────────────────────────────────────────────────────┘
         ↓ REST API Calls
┌──────────────────────────────────────────────────────────────────┐
│  BACKEND (Spring Boot - Port 8080)                              │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │  Controllers (4 files)                                     │ │
│  │  ├─ AuthController → /api/auth/*                         │ │
│  │  ├─ AdminController → /api/admin/*                       │ │
│  │  ├─ VendorController → /api/vendor/*                     │ │
│  │  └─ UserController → /api/user/*                         │ │
│  └────────────────────────────────────────────────────────────┘ │
│         ↓ Route to Service Layer                               │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │  Services (6 files)                                        │ │
│  │  ├─ AuthService (Login/Register)                          │ │
│  │  ├─ VendorService (Vendor ops)                            │ │
│  │  ├─ ProductService (Product CRUD)                         │ │
│  │  ├─ CartService (Cart management)                         │ │
│  │  ├─ OrderService (Order processing)                       │ │
│  │  └─ MembershipService (Membership ops)                    │ │
│  └────────────────────────────────────────────────────────────┘ │
│         ↓ Call Repository Layer                                │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │  Repositories (10 files)                                   │ │
│  │  ├─ UserRepository.findByEmail()                          │ │
│  │  ├─ ProductRepository.findByVendor()                      │ │
│  │  ├─ OrderRepository.findByUser()                          │ │
│  │  └─ ... (7 more repositories)                             │ │
│  └────────────────────────────────────────────────────────────┘ │
│         ↓ SQL Queries (JPA → Hibernate)                        │
└──────────────────────────────────────────────────────────────────┘
         ↓ Database Queries
┌──────────────────────────────────────────────────────────────────┐
│  DATABASE (MySQL - Port 3306)                                   │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │  10 Tables:                                                │ │
│  │  ├─ users                                                 │ │
│  │  ├─ vendors                                               │ │
│  │  ├─ products                                              │ │
│  │  ├─ cart & cart_items                                     │ │
│  │  ├─ orders & order_items & order_status                  │ │
│  │  ├─ memberships                                           │ │
│  │  └─ user_requests                                         │ │
│  └────────────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────────────┘
```

---

## 🚀 Quick Start Sequence

```
1. Clone Repository
   git clone https://github.com/yourusername/event-management-erp.git

2. Backend Setup
   └─ cd backend
      └─ mvn clean install
         └─ mvn spring-boot:run
            └─ Listens on http://localhost:8080

3. Database Setup
   └─ mysql -u root -p
      └─ SOURCE database/schema.sql
         └─ SOURCE database/seed_data.sql

4. Frontend Setup
   └─ cd frontend
      └─ Open index.html in browser
         └─ Accessible at http://localhost:3000 (or local server)

5. Test Application
   └─ Login with test credentials
      ├─ Admin: admin@event.com / admin123
      ├─ Vendor: john@vendor.com / vendor123
      └─ User: jane@user.com / user123
```

---

## 📊 Component Statistics

| Component | Count | Status |
|-----------|-------|--------|
| Java Files | 35+ | ✅ Complete |
| JavaScript Files | 6 | ✅ Complete |
| CSS Files | 3 | ✅ Complete |
| HTML Files | 5 | ✅ Complete |
| SQL Files | 5 | ✅ Complete |
| Configuration Files | 8 | ✅ Complete |
| Documentation Files | 15+ | ✅ Complete |
| Total Files | ~95 | ✅ Production Ready |

---

## ✨ Features Organized by File

| Feature | Backend File | Frontend File |
|---------|--------------|---------------|
| Authentication | AuthController.java | api.js → login() |
| User Management | AdminController.java | admin.js → loadAdminData() |
| Vendor Management | VendorController.java | vendor.js → loadVendorData() |
| Product Management | ProductService.java | vendor.js → handleAddProduct() |
| Shopping | CartService.java | user.js → addToCart() |
| Orders | OrderService.java | user.js → handlePlaceOrder() |
| Session Management | - | api.js → SessionManager |

---

**This blueprint represents a complete, production-ready, enterprise-grade full-stack application!** 🎉
