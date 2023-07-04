CREATE DATABASE Comparison_data;

USE Comparison_data;

CREATE TABLE GDP_comparison (
    id INT,
    Country VARCHAR(255),
    GDP_2010 DECIMAL(18, 2),
    GDP_2020 DECIMAL(18, 2),
    Population_2010 VARCHAR(255),
    Population_2020 VARCHAR(255),
    GDP_per_capita_2010 DECIMAL(18, 2),
    GDP_per_capita_2020 DECIMAL(18, 2)
);

INSERT INTO GDP_comparison (id, Country, GDP_2010, GDP_2020, Population_2010, Population_2020, GDP_per_capita_2010, GDP_per_capita_2020)
VALUES
    (1, 'United States', 14990000000000, 21430000000000, '309 million', '331 million', 48506, 64805),
    (2, 'China', 6100000000000, 16640000000000, '1.34 billion', '1.41 billion', 4559, 11775),
    (3, 'Japan', 5480000000000, 5080000000000, '128 million', '125 million', 42789, 40672),
    (4, 'Germany', 3420000000000, 3860000000000, '81 million', '83 million', 42157, 46530),
    (5, 'United Kingdom', 2620000000000, 2630000000000, '62 million', '67 million', 42365, 39135),
    (6, 'France', 2590000000000, 2580000000000, '62 million', '65 million', 41828, 39589),
    (7, 'Italy', 2180000000000, 1850000000000, '59 million', '60 million', 37028, 30973),
    (8, 'India', 1660000000000, 2940000000000, '1.20 billion', '1.38 billion', 1384, 2135),
    (9, 'Brazil', 2210000000000, 1430000000000, '195 million', '213 million', 11351, 6708),
    (10, 'Canada', 1610000000000, 1640000000000, '34 million', '38 million', 47457, 42701);

CREATE USER 'mrnvcode'@'localhost' IDENTIFIED BY 'Naman@123';
GRANT ALL PRIVILEGES ON *.* TO 'mrnvcode'@'localhost';
