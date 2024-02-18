create table books(
                      id serial primary key,
                      name varchar(256),
                      author varchar(256),
                      genre varchar(256),
                      price money,
                      quantity int
)
Create table users(
                      id serial primary key,
                      name varchar(256),
                      lastname varchar(256),
                      username varchar(256),
                      password varchar(256),
                      role_id int,
                      foreign key(role_id) references roles(id)
)

Create table roles(
                      id serial primary key,
                      name varchar(256)
)
Create table orders(
                       id serial primary key,
                       user_id int,
                       address varchar(256),
                       total_price decimal,
                       foreign key(user_id) references users(id)
);
Create table orders_books(
                             id serial primary key,
                             order_id int,
                             book_id int,
                             foreign key(order_id) references orders(id),
                             foreign key(book_id) references books(id)
)
create table genres(
                       id int primary key,
                       name varchar(256)
)

alter table books add column
    genre_id int

alter table books
    add constraint fk_genre_id
        foreign key (genre_id)
            references genres(id)

alter table books alter column price type decimal

create table genres(
                       id serial primary key,
                       name varchar(256)
)


alter table books add column
    genre_id int


alter table books
    add constraint fk_genre_id
        foreign key (genre_id)
            references genres(id)


insert into genres(name) values
('Horror'),
('Comedy'),
('Novel'),
('Sci-ficiton'),
('Romance')

insert into roles(name) values ('user'), ('admin')