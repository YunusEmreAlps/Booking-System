CREATE TABLE Booking(

isb varchar2(13), /* ISBN-13 number */
boo_nm varchar2(50) NOT NULL, /* Book name */
autho_nm varchar2(50) NOT NULL, /* Author name */
autho_gn varchar2(10) NOT NULL, /* Author gender */
publishe_nm varchar2(50) NOT NULL, /* Publisher name */
pri number(5,3), /* Price */

PRIMARY KEY(isb)
);


SELECT * FROM Booking;
SELECT isb as "ISBN",boo_nm as "BOOK NAME",autho_nm as "AUTHOR NAME",publishe_nm as "PUBLISHER NAME",pri as "PRICE" FROM Booking;
SELECT DISTINCT autho_nm,autho_gn FROM Booking;

--> WHERE
SELECT * From Booking WHERE autho_nm IS null;
SELECT * FROM Booking WHERE boo_nm='1984';
SELECT * FROM Booking WHERE NOT boo_nm='1984';
SELECT * FROM Booking WHERE pri=16.45;
SELECT * FROM Booking WHERE NOT publishe_nm='Can' AND NOT pri=27;

--> ORDER BY
SELECT * FROM Booking ORDER By isb asc;

--> INSERT INTO Booking VALUES(); 
INSERT INTO Booking(isb,boo_nm,autho_gn,publishe_nm,pri) VALUES('9789750738615','A','M','Can',16.45);
INSERT INTO Booking VALUES('9789750738615','Elon Musk','?','F','Buzdagi',30.45);

--> Update Booking SET ...
UPDATE Booking SET autho_nm='Ashlee Vance' WHERE isb='9789750738615';

--> Delete FROM Booking ... 
DELETE FROM Booking WHERE autho_nm='Ashlee Vance';

--> Functions (MIN,MAX,COUNT,AVG,SUM)
SELECT MIN(pri) FROM Booking; --> Minimum Price --> SELECT MIN(pri) FROM Booking;
SELECT MAX(pri) FROM Booking; --> Maximum Price --> SELECT MAX(pri) FROM Booking;
SELECT COUNT(*) FROM Booking;
SELECT AVG(pri) FROM Booking;
SELECT SUM(pri) FROM Booking;

--> LIKE
SELECT * FROM Booking WHERE autho_nm LIKE 'D%';
SELECT * FROM Booking WHERE boo_nm LIKE '1%';
SELECT * FROM Booking WHERE boo_nm LIKE '%ý';
SELECT * FROM Booking WHERE boo_nm LIKE '%a%';
SELECT * FROM Booking WHERE autho_nm LIKE 'D%y';
SELECT * FROM Booking WHERE autho_nm NOT LIKE 'D%';

--> WILDCARDS
SELECT * FROM Booking WHERE autho_nm LIKE '_a%';
SELECT * FROM Booking WHERE autho_nm LIKE '%[!D]%';

--> Group By
SELECT autho_nm,Count(autho_nm) FROM Booking Group By autho_nm;

SELECT autho_nm,COUNT(autho_nm) FROM Booking Group By autho_nm ORDER BY Count(autho_nm) desc;

select DISTINCT autho_nm FROM Booking;


