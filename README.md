# db-access

Your task is to integrate storage of teams, tournaments and matches from previous task with SQL databases using both pure JDBC API and Hibernate.
You should create a generic interface with all storage operations you do and then provide two implementations for it: one using JDBC and one using Hibernate. 

Please do not keep results in local collection field inside your implementation anymore. 
If you need to get data from DB - do the query!

Create 2 Pull Request. First one with Pure JDBC API. Second one with Hibernate.

CREATE TABLE Email(

        id BIGINT primary key generated always as identity,
        
        recipient_name varchar(100) not null,
        
        email_subject varchar(100) not null,
        
        email_body varchar(100) not nul,
        
        delivery_date timestamp not nul,
        
        is_sent boolean
)
