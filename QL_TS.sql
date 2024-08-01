CREATE DATABASE QUANLY_COFFEE;

USE QUANLY_COFFEE;

GO

CREATE TABLE Staff
(
	Staff_Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Name NVARCHAR(50) NOT NULL,
	Account NVARCHAR(50) NOT NULL,
	Password NVARCHAR(50) NOT NULL,
	Phone int NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	Address NVARCHAR(50) NOT NULL,
	Birthday Date NOT NULL,
	Status NVARCHAR(50) NOT NULL, 
	is_Staff BIT NOT NULL,
);
drop table Staff
CREATE TABLE Status
(
	Status_Id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Status_Name NVARCHAR(50) NOT NULL
);
select * from status
drop table Status
CREATE TABLE Sizes
(
	Size_Id INT IDENTITY(1,1) PRIMARY KEY,
	Size_Name NVARCHAR(50) NOT NULL,
)
drop table Status
CREATE TABLE Product_Sizes (
    Product_Size_Id INT IDENTITY(1,1) PRIMARY KEY,
    Product_Id INT NOT NULL,
    Size_Id INT NOT NULL,
    Price FLOAT  NOT NULL,
    FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id),
    FOREIGN KEY (Size_Id) REFERENCES Sizes(Size_Id)
);

CREATE TABLE Product_Type (
    Product_Type_Id INT IDENTITY(1,1) PRIMARY KEY,
    Product_Type_Name NVARCHAR(100) NOT NULL
);

CREATE TABLE Products 
(
	Product_Id INT IDENTITY(1,1) PRIMARY KEY,
	Images NVARCHAR(255),
	Name_Product NVARCHAR(100) NOT NULL,
	Price float NOT NULL,
	Description Text NOT NULL, 
	Priduct_Image NVARCHAR(255) NOT NULL,
	Product_Type_Id INT NOT NULL, 
	FOREIGN KEY (Product_Type_Id) REFERENCES Product_Type(Product_Type_Id)
)

CREATE TABLE Toppings
(
	Topping_Id INT IDENTITY(1,1) PRIMARY KEY,
	Name_Topping NVARCHAR(255) NOT NULL,
	Topping_Price FLOAT NOT NULL
)

CREATE TABLE Product_Toppings (
    Product_Topping_Id INT IDENTITY(1,1) PRIMARY KEY,
    Product_Id INT NOT NULL,
    Size_Id INT NOT NULL,
    Topping_Id INT NOT NULL,
    FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id),
    FOREIGN KEY (Size_Id) REFERENCES SizeS(Size_Id),
    FOREIGN KEY (Topping_id) REFERENCES Toppings(Topping_Id)
);

CREATE TABLE Customers (
    Customer_Id INT PRIMARY KEY IDENTITY(1,1),Phone NVARCHAR(20),
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Phone_Number NVARCHAR(100) NOT NULL UNIQUE,
	UNIQUE (Username),
    UNIQUE (Phone_Number)
);

CREATE TABLE Customer_Details (
    Detail_Id INT IDENTITY(1,1) PRIMARY KEY,
    Customer_Id INT NOT NULL,
    Full_Name NVARCHAR(100) NULL,
    Date_Of_Birth DATE NULL,
    Email NVARCHAR(100) NULL,
    Default_Address NVARCHAR(255) NULL,
    Gender NVARCHAR(10) NULL,
    Profile_Image NVARCHAR(255) NULL,
    FOREIGN KEY (Customer_Id) REFERENCES Customers(Customer_Id)
);

CREATE TABLE Orders (
    Order_Id INT IDENTITY(1,1) PRIMARY KEY,
    Customer_Id INT NOT NULL,
    Order_Date DATETIME NOT NULL DEFAULT GETDATE(),
    Total_Amount FLOAT NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    FOREIGN KEY (Customer_Id) REFERENCES Customers(Customer_Id)
);

-- Bảng lưu chi tiết đơn hàng
CREATE TABLE Order_Details (
    Order_Detail_Id INT IDENTITY(1,1) PRIMARY KEY,
    Order_Id INT NOT NULL,
    Product_Id INT NOT NULL,
    Size_Id INT NOT NULL,
    Quantity INT NOT NULL,
    Price FLOAT NOT NULL,
    Topping_Id INT NULL,
    FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id),
    FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id),
    FOREIGN KEY (Size_Id) REFERENCES Sizes(Size_Id),
    FOREIGN KEY (Topping_Id) REFERENCES Toppings(Topping_Id)
);

CREATE TABLE Shipping_Info (
    Shipping_Info_Id INT IDENTITY(1,1) PRIMARY KEY,
    Order_Id INT NOT NULL,
    Recipient_Name NVARCHAR(100) NOT NULL,
    Recipient_Phone NVARCHAR(15) NOT NULL,
    Address NVARCHAR(255) NOT NULL,
    Address_Note NVARCHAR(255) NULL,
    Delivery_Time DATETIME NOT NULL,
    FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id)
);

CREATE TABLE Payment_Method
(
	Payment_Method_Id INT IDENTITY(1,1) PRIMARY KEY,
	Payment_Method_Name NVARCHAR(100) NOT NULL
)

CREATE TABLE Payment_Status
(
	Payment_Status_Id INT IDENTITY(1,1) PRIMARY KEY,
	Payment_Status_Name NVARCHAR(100) NOT NULL
)

CREATE TABLE Payment_Info (
    Payment_Info_Id INT IDENTITY(1,1) PRIMARY KEY,
    Order_Id INT NOT NULL,
    Payment_Method_Id INT NOT NULL,
    Payment_Status_Id INT NOT NULL,
    FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id),
	FOREIGN KEY (Payment_Status_Id) REFERENCES Payment_Status(Payment_Status_Id),
	FOREIGN KEY (Payment_Method_Id) REFERENCES Payment_Method(Payment_Method_Id)
);
--Thêm dữ liệu vào bảng status
INSERT INTO Status VALUES 
(N'Đang làm'),
(N'Đã nghỉ')
SELECT * FROM Status
-- Thêm dữ liệu vào bảng staff
INSERT INTO Staff VALUES (N'Trương Tuấn Dũng','NV001','123','0394314552','tuandungtruong122@gmail.com',N'Quận Gò Vấp, TP.Hồ Chí Minh','10/08/2004',N'Đang Làm','1')
SELECT * FROM Staff
-- Thêm dữ liệu vào bảng Sizes
INSERT INTO Sizes (Size_Name) VALUES ('S');
INSERT INTO Sizes (Size_Name) VALUES ('M');
INSERT INTO Sizes (Size_Name) VALUES ('L');

-- Thêm dữ liệu vào bảng Product_Type
INSERT INTO Product_Type (Product_Type_Name) VALUES ('Coffee');
INSERT INTO Product_Type (Product_Type_Name) VALUES ('Tea');

-- Thêm dữ liệu vào bảng Products
INSERT INTO Products (Name_Product, Price, Description, Priduct_Image, Product_Type_Id) 
VALUES ('Espresso', 2.50, 'Strong coffee', 'espresso.jpg', 1);

INSERT INTO Products (Name_Product, Price, Description, Priduct_Image, Product_Type_Id) 
VALUES ('Green Tea', 1.50, 'Healthy tea', 'green_tea.jpg', 2);

-- Thêm dữ liệu vào bảng Product_Sizes
INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (1, 1, 2.50);
INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (1, 2, 3.00);
INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (1, 3, 3.50);

INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (2, 1, 1.50);
INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (2, 2, 2.00);
INSERT INTO Product_Sizes (Product_Id, Size_Id, Price) VALUES (2, 3, 2.50);

-- Thêm dữ liệu vào bảng Toppings
INSERT INTO Toppings (Name_Topping, Topping_Price) VALUES ('Whipped Cream', 0.50);
INSERT INTO Toppings (Name_Topping, Topping_Price) VALUES ('Chocolate Syrup', 0.75);

-- Thêm dữ liệu vào bảng Product_Toppings
INSERT INTO Product_Toppings (Product_Id, Size_Id, Topping_Id) VALUES (1, 2, 1);
INSERT INTO Product_Toppings (Product_Id, Size_Id, Topping_Id) VALUES (1, 2, 2);

-- Giả sử khách hàng mua sản phẩm 'Espresso' với kích thước 'M' và topping 'Whipped Cream'
DECLARE @ProductId INT = 1;
DECLARE @SizeId INT = 2;
DECLARE @ToppingId INT = 1;

SELECT 
    p.Name_Product,
    s.Size_Name,
    t.Name_Topping,
    ps.Price AS Product_Price,
    t.Topping_Price,
    (ps.Price + t.Topping_Price) AS Total_Price
FROM 
    Products p
    JOIN Product_Sizes ps ON p.Product_Id = ps.Product_Id
    JOIN Sizes s ON ps.Size_Id = s.Size_Id
    JOIN Toppings t ON t.Topping_Id = @ToppingId
WHERE 
    p.Product_Id = @ProductId 
    AND ps.Size_Id = @SizeId;