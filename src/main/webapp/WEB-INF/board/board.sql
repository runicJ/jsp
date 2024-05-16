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
	complaint char(2) default 'NO'  /* 신고글 유무(신고중:OK, 정상글:NO) alter table add column */
	primary key(idx),   /* 기본키 : 고유번호 */
	foreign key(mid) references member2(mid)  /* 변경(restrict) */
);

desc board;
drop table board;

insert into board values (default,'admin','관리맨','게시판 서비스를 시작합니다.','즐거운 게시판 생활이 되세요.',default,'192.168.50.57',default,default,default);

select * from board;
select * from board where idx = 9;  /* 현재글 - idx,title content에서 이 변수가 이미 사용중 같으면 사용할 수 없음 => 이전글 다음글 같은 페이지 */
select idx,title from board where idx > 9 order by idx limit 1;  /* 다음글 // 다음글이 꼭 10번일거란 보장이 없으므로 9번보다 크고 정렬해서 limit 함 */
select idx,title from board where idx < 9 order by idx desc limit 1;  /* 이전글 - idx랑 title 가져감 */

ALTER TABLE board AUTO_INCREMENT = 1;

-- 시간으로 비교해서 필드에 값 저장하기
select *, timestampdiff(hour, wDate, now()) as hour_diff from board;  /* 옵션, 비교1, 비교데이터2 / 2-1 => 사용하려면 DAO 어디서 나오는지 이건 게시글리스트 */

-- 날짜로 비교해서 필드에 값 저장하기 date_format(visitDate,  '%Y%m%d')
select *, datediff(wDate, now()) as hour_diff, date_format(wDate, '%Y-%m-%d %H:%i:%s') from board;  /* 날짜만 비교 datediff(날짜를 비교하겠다고 선언했기 때문에 옵션이 필요없음) => - 나오면 지났다는 것 */

--<marquee behavior="alternate" scrollamount="1000">깜박깜박</marquee>