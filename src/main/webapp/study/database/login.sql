show tables;

desc hoewon;

insert into hoewon values (default,'홍길동',25,'남자','서울');

select * from hoewon order by idx desc;

ALTER TABLE hoewon AUTO_INCREMENT = 1;