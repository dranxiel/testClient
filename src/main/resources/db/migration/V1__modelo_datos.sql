CREATE TABLE Suppliers
(
    SupplierID   INTEGER PRIMARY KEY AUTOINCREMENT,
    CompanyName  varchar(100) NOT NULL UNIQUE,
    ContactName  varchar(100),
    ContactTitle varchar(100),
    Address      varchar(200),
    City         varchar(100),
    Region       varchar(100),
    PostalCode   varchar(100),
    Country      varchar(100),
    Phone        varchar(50),
    Fax          varchar(50),
    HomePage     varchar(100)
);
CREATE INDEX IDX_Suppliers_CompanyName on Suppliers (CompanyName);
CREATE INDEX IDX_Suppliers_PostalCode on Suppliers (PostalCode);
CREATE TABLE Employees
(
    EmployeeID      INTEGER PRIMARY KEY AUTOINCREMENT,
    LastName        varchar(100) NOT NULL,
    FirstName       varchar(100) NOT NULL,
    Title           varchar(100),
    TitleOfCourtesy varchar(100),
    BirthDate       DATETIME,
    HireDate        DATETIME,
    Address         varchar(200),
    City            varchar(100),
    Region          varchar(100),
    PostalCode      varchar(100),
    Country         varchar(100),
    HomePage        varchar(100),
    Extension       varchar(10),
    Photo           TEXT,
    ReportsTo       TEXT
);
CREATE INDEX IDX_Employees_LastName on Employees (LastName);
CREATE INDEX IDX_Employees_FirstName on Employees (FirstName);
CREATE TABLE Categories
(
    CategoryID   INTEGER PRIMARY KEY AUTOINCREMENT,
    CategoryName varchar(100) NOT NULL UNIQUE,
    Description  varchar(200) ,
    Picture      TEXT
);

CREATE TABLE Products
(
    ProductID       INTEGER PRIMARY KEY AUTOINCREMENT,
    ProductName     varchar(100) NOT NULL,
    SupplierID      INTEGER
        CONSTRAINT fk_Products_SupplierID references Suppliers (SupplierID),
    CategoryID      INTEGER
        CONSTRAINT fk_Products_CategoryID references Categories (CategoryID),
    QuantityPerUnit INTEGER,
    UnitsPrice      DECIMAL(10, 2),
    UnitsInStock    INTEGER,
    UnitsOnOrder    INTEGER,
    ReorderLevel    INTEGER,
    Discontinued    boolean
);
CREATE INDEX IDX_Products_ProductName on Products (ProductName);
CREATE TABLE Customers
(
    CustomerID   INTEGER PRIMARY KEY AUTOINCREMENT,
    CompanyName  varchar(100) NOT NULL UNIQUE,
    ContactName  varchar(100),
    ContactTitle varchar(100),
    Address      varchar(200),
    City         varchar(100),
    Region       varchar(100),
    PostalCode   varchar(100),
    Country      varchar(100),
    Phone        varchar(50),
    Fax          varchar(50)
);
CREATE INDEX IDX_Customers_CompanyName on Customers (CompanyName);
CREATE TABLE Shippers
(
    ShipperId   INTEGER PRIMARY KEY AUTOINCREMENT,
    CompanyName varchar(100) NOT NULL UNIQUE,
    Phone       varchar(50)
);
CREATE TABLE Orders
(
    OrderID           INTEGER PRIMARY KEY AUTOINCREMENT,
    CustomerID        integer
        CONSTRAINT fk_Orders_CategoryID references Customers (CustomerID),
    EmployeeId        integer
        CONSTRAINT fk_Orders_EmployeeID references Employees (EmployeeID),
    OrderDate         DATETIME,
    RequiredDate      DATETIME,
    ShippedDate       DATETIME,
    ShipVia           varchar(100),
    Freight           varchar(100),
    ShipName          varchar(100),
    ShipAddress       varchar(200),
    ShipCity          varchar(100),
    ShipRegion        varchar(100),
    ShipPostalCode    varchar(100),
    ShipPostalCountry varchar(100),
    ShipperID         INTEGER
        CONSTRAINT fk_Orders_ShipperId references Shippers (ShipperId)
);
CREATE TABLE OrderDetails
(
    OrderID    INTEGER
        CONSTRAINT fk_OrderDetails_EmployeeID references Orders (OrderID),
    ProductID  INTEGER
        CONSTRAINT fk_OrderDetails_ProductID references Products (ProductID),
    UnitsPrice DECIMAL(10, 2) not null,
    Quantity   integer        not null,
    Discount   boolean        not null,
    primary key (OrderID, ProductID),
    unique (OrderID, ProductID)
);