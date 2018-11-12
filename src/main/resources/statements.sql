
SELECT * FROM addresses;

SELECT * FROM emails;

SELECT e.id,a.address,e.subject,e.text,e.sentDate FROM emails e JOIN addresses a ON e.to_addr_id=a.id;

INSERT IGNORE INTO addresses (address) VALUES ('zabidovski@gmail.com');

TRUNCATE TABLE emails;

SHOW TABLES;
DROP TABLE IF EXISTS emails;
DROP TABLE IF EXISTS addresses;
