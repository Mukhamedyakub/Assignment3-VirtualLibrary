create table books(
                      id serial primary key,
                      name varchar(256),
                      author varchar(256),
                      genre varchar(256),
                      price money,
                      quantity int
)