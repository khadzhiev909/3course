CREATE TABLE cars (
    id int primary key,
    brand varchar(255),
    model varchar(255),
    cost int
);

CREATE TABLE drivers (
    id int primary key,
    name varchar(255),
    age int,
    has_license boolean,
    car_id int references car(id)
);