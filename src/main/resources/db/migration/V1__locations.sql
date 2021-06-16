create table locations (id bigint auto_increment, loc_name varchar(255), lat double, lon double, primary key(id));
insert into locations(loc_name, lat, lon) values ('Budapest', 40.5, 17.5);
insert into locations(loc_name, lat, lon) values ('Szeged', 44.2, 19.3);
