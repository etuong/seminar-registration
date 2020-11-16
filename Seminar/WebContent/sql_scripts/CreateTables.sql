CREATE TABLE User (
  UserID INT NOT NULL AUTO_INCREMENT, 
  Name VARCHAR(50), 
  Email VARCHAR(50), 
  EmploymentStatus VARCHAR(100),
  PRIMARY KEY (UserID)  
);

create table Course
(
   CourseID INT NOT NULL primary key,
   Code varchar(50) not null unique,
   Name varchar(50) not null unique
);

insert into Course (CourseID, Code, Name)
values
  (1, 'A1', 'J2EE Design Patterns'),
  (2, 'A2', 'Enterprise Service Bus'),
  (3, 'A3', 'Service Oriented Architectures'),
  (4, 'A4', 'Web Services'),
  (5, 'A5', 'Web Services Security'),
  (6, 'A6', 'Secure Messaging');
  
create table Registration
(
  RegistrationID int NOT NULL AUTO_INCREMENT,
  UserID integer not null,
  CourseID integer not null,
  PRIMARY KEY (RegistrationID),
  foreign key (UserID) references User (UserID),
  foreign key (CourseID) references Course (CourseID)
) 

create table Accommodation
(
   ID INT NOT NULL AUTO_INCREMENT primary key ,
   isParking BOOLEAN,
   isHotel BOOLEAN,
   
   UserId integer not null,
   foreign key (UserId) references User(UserId),
);
