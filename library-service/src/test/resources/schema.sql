
create  table if not exists author(author_id long auto_increment , first_name varchar(256), last_name varchar(256), email varchar(256) );
create  table if not exists book (book_id long auto_increment, title varchar(256),   publisher varchar(256), borrowed char(1) default 'N', borrower_id number,  author_id number);

