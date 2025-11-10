-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

-- Clear existing data
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE products;
TRUNCATE TABLE cart;
SET FOREIGN_KEY_CHECKS = 1;

-- Seed Users
INSERT INTO users (name, email, password) VALUES
('John Doe', 'john@example.com', 'password123'),
('Jane Smith', 'jane@example.com', 'password123'),
('Mike Johnson', 'mike@example.com', 'password123'),
('Sarah Wilson', 'sarah@example.com', 'password123'),
('Robert Brown', 'robert@example.com', 'password123');

-- Seed Products
INSERT INTO products (name, price, image) VALUES
('Smartphone', 699.99, 'smartphone.jpg'),
('Laptop', 1299.99, 'laptop.jpg'),
('Wireless Headphones', 199.99, 'headphones.jpg'),
('Smart Watch', 299.99, 'smartwatch.jpg'),
('Tablet', 499.99, 'tablet.jpg'),
('Gaming Console', 499.99, 'console.jpg'),
('Bluetooth Speaker', 79.99, 'speaker.jpg'),
('Wireless Mouse', 29.99, 'mouse.jpg'),
('Keyboard', 89.99, 'keyboard.jpg'),
('External Hard Drive', 129.99, 'harddrive.jpg');

-- Create cart table if it doesn't exist
CREATE TABLE IF NOT EXISTS cart (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    quantity INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Seed Cart with sample items
INSERT INTO cart (user_id, product_id, quantity) VALUES
(1, 1, 2),  -- John has 2 smartphones in cart
(1, 3, 1),  -- John has 1 headphone in cart
(2, 2, 1),  -- Jane has 1 laptop in cart
(2, 5, 1),  -- Jane has 1 tablet in cart
(3, 4, 1),  -- Mike has 1 smartwatch in cart
(3, 7, 2);  -- Mike has 2 bluetooth speakers in cart