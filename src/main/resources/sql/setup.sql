--This file is not use for auto ddl generation via Hibernate. It's only used to document sql ddl for create table in case, well, we need it :)
CREATE TABLE EMPLOYEE(
    id INT NOT NULL auto_increment, 
    name VARCHAR(50) NOT NULL,
    joining_date DATE NOT NULL,
    salary DOUBLE NOT NULL,
    ssn VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

commit;