CREATE DATABASE payroll_service;

USE payroll_service;

## use case 2

DROP TABLE employee_payroll;
CREATE TABLE employee_payroll(
employeeId INT AUTO_INCREMENT,
employeeName VARCHAR(30) NOT NULL,
employeeSalary DOUBLE,
startDate DATE ,
PRIMARY KEY(employeeId)
);

DESC employee_payroll;

### use case 3
INSERT INTO employee_payroll VALUES(1 , 'Mahidhar Reddy' , 10000 , '2023-12-08');
INSERT INTO employee_payroll VALUES(2 , 'Karthik' , 2500 , '2023-11-08');
INSERT INTO employee_payroll VALUES(3 , 'Mani Chandra' , 25000 , '2021-11-08');



### usecase 4
SELECT * FROM employee_payroll;


### usecase 5

SELECT * FROM employee_payroll WHERE startDate BETWEEN CAST('2022-01-01' AS DATE) AND CURDATE();


### usecase 6 

ALTER TABLE employee_payroll ADD COLUMN gender VARCHAR(1);
INSERT INTO employee_payroll VALUES(4 , 'Bill' , 25000 , '2021-11-08' , NULL);
INSERT INTO employee_payroll VALUES(5 , 'Ani' , 25000 , '2022-11-08' , NULL);
INSERT INTO employee_payroll VALUES(6 , 'Chaandini' , 22000 , '2022-07-08' , NULL);

UPDATE employee_payroll SET gender = 'M'
WHERE employeeName LIKE 'M%'
OR employeeName LIKE 'K%'
OR employeeName LIKE 'B%';

UPDATE employee_payroll SET gender = 'F'
WHERE employeeName LIKE 'A%'
OR employeeName LIKE 'C%' ;

SELECT * FROM employee_payroll;
### use case 7

SELECT 
gender ,
COUNT(employeeId) AS count, 
SUM(employeeSalary) AS totalSum , 
AVG(employeeSalary) AS average , 
MAX(employeeSalary) AS maxSalary,
MIN(employeeSalary) AS minSalary 
FROM employee_payroll GROUP BY gender;

### usecase 8

ALTER TABLE employee_payroll ADD COLUMN
(
employeeDepartment VARCHAR(255) NOT NULL,
employeePhno VARCHAR(10),
employeeAddress VARCHAR(255) DEFAULT 'Bombay',
employeeCity VARCHAR(255),
employeeCountry VARCHAR(255)
);


INSERT INTO employee_payroll VALUES(7 , 'Terissa' , 25000 , '2022-11-08' , 'F' ,'HR' , '9492614272' , 'Hyderabad' ,'Hyderabad'  , 'India');
INSERT INTO employee_payroll VALUES(8 , 'Terissa' , 55000 , '2022-10-08' , 'F' ,'SALES' ,'9492654272' , 'KURNOOL' , 'KURNOOL' , 'India');

SELECT * FROM employee_payroll WHERE employeeName = 'Terissa' AND employeeDepartment = 'HR';
###usecase 9

ALTER TABLE employee_payroll ADD COLUMN
(
basicPay FLOAT,
deductions FLOAT,
taxablePay FLOAT,
incomeTax FLOAT,
netPay FLOAT
);

UPDATE employee_payroll
SET
basicPay = 20000,
deductions = 2000,
taxablePay = 1000,
incomeTax = 200,
netPay = 18000
Where employeeName = 'Terissa' AND employeeDepartment = 'HR';

SELECT * FROM employee_payroll WHERE employeeName = 'Terissa' AND employeeDepartment = 'HR';


SELECT * FROM employee_payroll;


DROP TABLE employee_payroll;



### according to normalization database is updated

/*

Company -> companyId(primary key) , companyname;

Employee -> employeeId(primary key) , companyId(foreign key) , name , phone number , 

Department -> departmentId(primary key) , departName

employeeDepartment -> empId(foreignkey) , departmentId(foreign key)

payroll -> empid(foreign key) , basicpay , deductions , taxablepay , tax , netpay

Address -> empid(foreign key) , street , village , city , state , zip 

*/

USE payroll_service;

CREATE TABLE company(
companyId VARCHAR(255) PRIMARY KEY,
companyName VARCHAR(255) NOT NULL
);

CREATE TABLE employee(
employeeId VARCHAR(255) PRIMARY KEY,
employeeName VARCHAR(255) NOT NULL,
employeePhoneNo VARCHAR(255) NOT NULL,
employeeGender VARCHAR(1) ,
companyId VARCHAR(255) ,
FOREIGN KEY(companyId) REFERENCES company(companyId)
);

CREATE TABLE department(
departmentId VARCHAR(255) PRIMARY KEY,
departmentName VARCHAR(255) NOT NULL
);

CREATE TABLE employee_department(
employeeId VARCHAR(255),
departmentId VARCHAR(255),
FOREIGN KEY(employeeId) REFERENCES employee(employeeId),
FOREIGN KEY(departmentId) REFERENCES department(departmentId),
PRIMARY KEY(employeeId , departmentId)
);

CREATE TABLE payroll(
employeeId VARCHAR(255) PRIMARY KEY,
basicPay DOUBLE NOT NULL,
deductions DOUBLE NOT NULL,
taxablePay DOUBLE NOT NULL,
tax DOUBLE NOT NULL,
netPay DOUBLE NOT NULL,
FOREIGN KEY(employeeId) REFERENCES employee(employeeId)
);

CREATE TABLE employee_address(
employeeId VARCHAR(255) PRIMARY KEY,
street VARCHAR(255),
village VARCHAR(255),
city VARCHAR(255),
state VARCHAR(255),
zip VARCHAR(255),
country VARCHAR(255),
FOREIGN KEY(employeeId) REFERENCES employee(employeeId)
);

DESC company;
DESC employee;
DESC department;
DESC employee_department;
DESC payroll;
DESC employee_address;

CREATE TABLE payroll_service_logs(
logId INT AUTO_INCREMENT PRIMARY KEY,
message VARCHAR(255),
logType VARCHAR(255)
);
DROP TABLE payroll_service_logs;

### COMPANY TRIGGERS

CREATE TRIGGER company_added_trigger 
BEFORE INSERT ON company 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Company has added" , NEW.companyName );

CREATE TRIGGER company_update_trigger 
BEFORE UPDATE ON company 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Company has updated" , NEW.companyName );

CREATE TRIGGER company_delete_trigger 
BEFORE DELETE ON company 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Company has deleted" , OLD.companyName );

### EMPLOYEE TRIGGERS
CREATE TRIGGER employee_added_trigger 
BEFORE INSERT ON employee 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee has added" , NEW.employeeName );

CREATE TRIGGER employee_update_trigger 
BEFORE UPDATE ON employee 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee has updated" , NEW.employeeName );

CREATE TRIGGER employee_delete_trigger 
BEFORE DELETE ON employee 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee has deleted" , OLD.employeeName );

### payroll triggers
CREATE TRIGGER payroll_added_trigger 
BEFORE INSERT ON payroll 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("payroll has added" , NEW.employeeId );

CREATE TRIGGER payroll_update_trigger 
BEFORE UPDATE ON payroll 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("payroll has updated" , NEW.employeeId );

CREATE TRIGGER payroll_delete_trigger 
BEFORE DELETE ON payroll 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("payroll has deleted" , OLD.employeeId );

### department triggers

CREATE TRIGGER department_added_trigger 
BEFORE INSERT ON department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("department has added" , NEW.departmentName );

CREATE TRIGGER department_update_trigger 
BEFORE UPDATE ON department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("department has updated" , NEW.departmentName );

CREATE TRIGGER department_delete_trigger 
BEFORE DELETE ON department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("department has deleted" , OLD.departmentName );

### employee_department_triggers
CREATE TRIGGER employee_department_added_trigger 
BEFORE INSERT ON employee_department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee department has added" , NEW.employeeId );

CREATE TRIGGER employee_department_update_trigger 
BEFORE UPDATE ON employee_department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee department has updated" ,  NEW.employeeId);

CREATE TRIGGER employee_department_delete_trigger 
BEFORE DELETE ON employee_department 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("employee department has deleted" ,  OLD.employeeId);

### address triggers
CREATE TRIGGER employee_address_added_trigger 
BEFORE INSERT ON employee_address 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Address has added" , NEW.employeeId );

CREATE TRIGGER employee_address_update_trigger 
BEFORE UPDATE ON employee_address 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Address has updated" , NEW.employeeId );

CREATE TRIGGER employee_address_delete_trigger 
BEFORE DELETE ON employee_address 
FOR EACH ROW INSERT INTO payroll_service_logs(message , logType) VALUES ("Address has deleted" , OLD.employeeId );





INSERT INTO company VALUES ("GE1" , "GE DIGITAL");
INSERT INTO company VALUES ("GE2" , "GE ELECTRICAL");

INSERT INTO employee VALUES ("emp1" , "Mahidhar Reddy" , "9492614272" , "M" , "GE1");
INSERT INTO employee VALUES ("emp2" , "Sumit" , "9495614272" , "M" , "GE1");
INSERT INTO employee VALUES ("emp3" , "Terisa" , "9495785672" , "F" , "GE2");

INSERT INTO department VALUES ("dep_hr" , "HR");
INSERT INTO department VALUES ("dep_sales" , "Sales");
select * from employee;
SELECT * FROM employee_department;
delete from employee_department where employeeId="employeetest";

INSERT INTO employee_department VALUES ("emp1" , "dep_hr");
INSERT INTO employee_department VALUES ("emp2" , "dep_sales");
INSERT INTO employee_department VALUES ("emp3" , "dep_hr");
INSERT INTO employee_department VALUES ("emp3" , "dep_sales");

INSERT INTO payroll VALUES ("emp1" , 40000 , 2500 , 37500 , 500 , 37000);
INSERT INTO payroll VALUES ("emp2" ,  50000 , 2500 , 47500 , 500 , 47000);
INSERT INTO payroll VALUES ("emp3" ,  100000 , 10000 , 90000 , 10000 , 80000);


INSERT INTO employee_address VALUES ("emp1" , "2-40 vadapalani" , "chennai" , "chennai" , "TN" , "546789" , "India");
INSERT INTO employee_address VALUES ("emp2" ,   "3-40 near bustand" , "nandavaram" , "kurnool" , "AP" , "518124" , "India");
INSERT INTO employee_address VALUES ("emp3" ,  "2-41 vadapalani" , "chennai" , "chennai" , "TN" , "546789" , "India");

use payroll_service;
SELECT * FROM payroll WHERE employeeId="emp3";
SELECT employee.*, payroll.* , employee_department.* , company.*
FROM employee
JOIN payroll ON employee.employeeId = payroll.employeeId
JOIN company ON employee.companyId = company.companyId
JOIN employee_department ON employee.employeeId = employee_department.employeeId
WHERE employee.employeeName = 'Terisa';

SELECT employee.*, payroll.* , employee_department.* , company.*
FROM employee
JOIN payroll ON employee.employeeId = payroll.employeeId
JOIN company ON employee.companyId = company.companyId
JOIN employee_department ON employee.employeeId = employee_department.employeeId
WHERE company.companyId = 'GE1';

SELECT employee.employeeGender , SUM(payroll.netPay)  , company.*
FROM employee
JOIN payroll ON employee.employeeId = payroll.employeeId
JOIN company ON employee.companyId = company.companyId
JOIN employee_department ON employee.employeeId = employee_department.employeeId
WHERE company.companyId LIKE 'GE%' GROUP BY companyId , employeeGender;



DELETE FROM employee_address;
SELECT * FROM company;
SELECT * FROM payroll_service_logs;