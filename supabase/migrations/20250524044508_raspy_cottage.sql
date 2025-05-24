DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    price DOUBLE NOT NULL,
    image_url VARCHAR(255),
    stock INTEGER NOT NULL,
    description TEXT,
    category_name VARCHAR(50),
    publication_date DATE,
    status INTEGER NOT NULL DEFAULT 1
);