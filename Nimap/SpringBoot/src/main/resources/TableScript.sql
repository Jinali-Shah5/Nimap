drop database if exists category_db;
create database category_db;
use category_db;
create table category (
  category_id int auto_increment not null ,
  category_name varchar(20),
  
  constraint ps_category_pk primary key (category_id)
);
insert into category(category_id,category_name) values (100,'Mobile');
insert into category(category_id,category_name) values (102,'Television');
insert into category(category_id,category_name) values (103,'Computer');

create table product (
  product_id int not null,
  product_name varchar(20),
  price int,
  prod_id int
  constraint product_pk primary key (product_id),
  constraint fk_product_category foreign key (prod_id) references category(category_id)
);
insert into product values(10, 'Redmi',17000,100);
insert into product values(11, 'Oppo',18000,100);
insert into product values(12, 'Samsung',19000,100);
insert into product values(13, 'Sony',54000,102);
insert into product values(14, 'LG',30000,102);
insert into product values(15, 'HP',40000,103);
insert into product values(16, 'Lenovo',35000,103);
insert into product values(17, 'Acer',50005,103);
commit;
select * from category;
select * from product;