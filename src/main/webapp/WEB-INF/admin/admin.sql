show tables;

/* 신고테이블(complaint) */
create table complaint (
	idx   int not null auto_increment,  /* 신고테이블 고유번호 */
	part  varchar(15) not null,  /* 신고 분류(게시판:board, 자료실:pds, 방명록:guest) */
	partIdx  int not null,  /* 신고분류항목 글의 고유번호 */
	cpMid  varchar(20) not null,  /* 신고자 아이디 */
	cpContent  text not null,  /* 신고 사유 */
	cpDate  datetime default now(),  /* 신고한 날짜 */
	primary key(idx)
);
desc complaint;

insert into complaint values (default,'board',6,'hkd1234','불쾌한 문구',default);

select * from complaint;

select c.*, date_format(c.cpDate, '%Y-%m-%d %H:%i') as cpDate, b.title, b.nickName, b.mid as 피신고인 from complaint c, board b where c.partIdx = b.idx;  /* cpDate를 넣을 경우 앞에꺼 씹고 뒤에 것이 덮어써서 들어감 // on이 아니라 ,했으니까 where로 해야함 // Join 하는 것 */

/* 리뷰 테이블 */
create table review(
	idx  int not null auto_increment,  /* 리뷰 고유번호 */
	part  varchar(20) not null,        /* 분야(게시판:board, 자료실:pds....) */
	partIdx  int not null,             /* 해당 분야의 고유번호 */
	mid  varchar(20) not null,         /* 리뷰 작성자 아이디 */
	nickName  varchar(30) not null,    /* 리뷰 작성자 닉네임 */
	star  int not null default 0,      /* 리뷰 별점 점수 */
	content  text,                     /* 리뷰 내용 */
	rDate  datetime default now(),     /* 리뷰 작성일 */
	primary key(idx),
	foreign key(mid) references member2(mid)
);
desc review;

ALTER TABLE review AUTO_INCREMENT = 1;