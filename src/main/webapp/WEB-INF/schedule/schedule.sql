show tables;

create table schedule(
	idx  int not null auto_increment,  /* 스케쥴관리 고유번호 */
	mid  varchar(20) not null,         /* 회원 아이디(일정검색시 필요), 본인만봄(관리자x(공통된 날) */
	sDate  datetime not null,          /* 일정 등록 날짜 / 찍은날짜가 일정을 등록하는 날짜가 되기에 default가 없다 */
	part  varchar(10) not null,        /* 일정분류(1.모임, 2.업무, 3.학습, 4.여행) / 내용하고 싶으면 title 내용 */
	content text not null,             /* 일정 상세내역 */
	primary key(idx),
	foreign key(mid) references member2(mid)
);

desc schedule;
/* 시작일 마감일 정하지 않고 관리자가 미리 정해놓고 개개인이 클릭해서 예약하도록 */
insert into schedule value(default, 'hkd1234', '2024-05-3', '모임', '중학교동창회');
insert into schedule value(default, 'soso1234', '2024-05-3', '모임', '초등학교동창회');
insert into schedule value(default, 'admin', '2024-05-4', '업무', '시스템점검');
insert into schedule value(default, 'hkd1234', '2024-05-5', '업무', '주간회의');
insert into schedule value(default, 'hkd1234', '2024-05-7', '모임', '고등학교동창회');
insert into schedule value(default, 'hkd1234', '2024-05-7', '업무', '프로젝트기획');
insert into schedule value(default, 'hkd1234', '2024-05-7', '학습', '파이썬 공부');
insert into schedule value(default, 'hkd1234', '2024-05-7', '학습', '코딩 공부');
insert into schedule value(default, 'hkd1234', '2024-05-9', '업무', '기획팀 5월 정기 총회');
insert into schedule value(default, 'hkd1234', '2024-05-11', '학습', '영어스피킹');
insert into schedule value(default, 'aris1234', '2024-05-13', '모임', '초등학교동창회');
insert into schedule value(default, 'hkd1234', '2024-05-13', '업무', '종소세 신고');
insert into schedule value(default, 'hkd1234', '2024-05-23', '여행', '동해서핑');
insert into schedule value(default, 'aris1234', '2024-05-18', '모임', '초등학교동창회');
insert into schedule value(default, 'hkd1234', '2024-05-22', '업무', '사내 멘토링');
insert into schedule value(default, 'hkd1234', '2024-05-19', '모임', '밴드 동호회');
insert into schedule value(default, 'aris1234', '2024-05-20', '여행', '내장산산악회');
insert into schedule value(default, 'hkd1234', '2024-05-28', '모임', '식도락 동호회');
insert into schedule value(default, 'hkd1234', '2024-06-2', '업무', '보고서 검토');
insert into schedule value(default, 'hkd1234', '2024-06-15', '여행', '꿈속으로여행');
insert into schedule value(default, 'aris1234', '2024-06-13', '모임', '초등학교동창회');
insert into schedule value(default, 'hkd1234', '2024-06-20', '학습', '중국어공부');
insert into schedule value(default, 'admin', '2024-06-22', '모임', '개발자 다과회');

select * from schedule;

select * from schedule where mid = 'hkd1234' and substring(sDate,1,10) = '2024-05-13' order by sDate;  /* 문자 형식으로 비교 */
select * from schedule where mid = 'hkd1234' and date_format(sDate, '%Y-%m-%d') = '2024-05-07' order by sDate;  /* 날짜 형식으로 바꿔서 비교 */

select * from schedule where mid = 'hkd1234' and date_format(sDate, '%Y-%m') = '2024-05' order by sDate, part;
select * from schedule where mid = 'hkd1234' and date_format(sDate, '%Y-%m-%d') = '2024-05-07' group by sDate, part order by sDate, part;

select *,count(*) as cnt from schedule where mid = 'hkd1234' and date_format(sDate, '%Y-%m-%d') = '2024-05-07' group by sDate order by sDate, part;
select *,count(*) as cnt from schedule where mid = 'hkd1234' and date_format(sDate, '%Y-%m-%d') = '2024-05-07' group by part order by sDate, part;