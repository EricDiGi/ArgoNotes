CREATE USER 'service'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY 'service_engine';
GRANT ALL PRIVILEGES ON * . * TO 'service'@'localhost';
FLUSH PRIVILEGES;