show tables;

create table board (
	idx  int not null auto_increment,  /* 게시글의 고유번호 */
	mid  varchar(20) not null,  /* 게시글 작성자 아이디(게시글에는 닉네임이 올라가지만, 닉네임을 바꿀 경우 식별을 아이디로 함) */
	nickName  varchar(20) not null,  /* 게시글 작성자 닉네임 */
	title  varchar(100) not null,  /* 게시글 제목 */
	content  text not null,  /* 글 내용 */
	readNum  int default 0,  /* 글 조회수 */
	hostIp  varchar(40) not null,  /* 글 작성자 IP */
	openSw  char(2) default 'OK',  /* 게시글 공개 여부(OK:공개, NO:비공개) */
	wDate  datetime default now(),  /* 게시글 작성일 */
	good  int default 0,  /* '종아요' 클릭 횟수 누적 */
	primary key(idx),   /* 기본키 : 고유번호 */
	foreign key(mid) references member2(mid)  /* 변경(restrict) */
);

desc board;

drop table board;

insert into board values (default,'admin','관리맨','게시판 서비스를 시작합니다.','즐거운 게시판 생활이 되세요.',default,'192.168.50.57',default,default,default);

select * from board;