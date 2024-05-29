show tables;

/* 입/출금 거래내역 테이블 */
create table bankBook (
  idx   int  not null  auto_increment,  /* 입/출금 고유번호 */
  mid  varchar(20)  not null,  /* 입/출금자 아이디 */
  balance int,  /* 입/출금액 */
  primary key(idx)
);

desc bankBook;
delete from bankBook;
drop table bankBook;

insert into bankBook values (default, 'hkd1234', 1000000);
select * from bankBook;

/* 입출금 내역(적요) 테이블 */
create table bankBookHistory (
  idx   int  not null  auto_increment,  /* 입/출금 내역 고유번호 */
  bankBookIdx int not null,  /* 입/출금액 사용자의 고유번호 */
  content varchar(50) not null,  /* 입/출금액 내역서 */
  primary key(idx)
  /*foreign key(bankBookIdx) references bankBook(idx)*/  /* 원래 연결해 놓는 것이 맞음 */
);

desc bankBookHistory;
delete from bankBookHistory;
drop table bankBookHistory;

select * from bankBookHistory;