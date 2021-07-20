/* 
	SQL Script File : member_ddl.sql
	Member Sehema
*/

-- drop table
drop table member cascade constraints purge;

-- create table : 
create table member (
	member_id varchar2(30) primary key,
	member_pw varchar2(20) not null,
	name varchar2(20) not null,
	mobile varchar2(13) not null,
	email varchar2(30) not null,
	entry_date varchar2(10) not null,
	grade varchar2(1) not null,
	mileage number(6),
	manager varchar2(20)
);

-- init insert : 

update member set email='user01@work.com' where member_id='user01';

insert into member(member_id, member_pw, name, mobile, email, entry_date, grade, mileage)
values('user01', 'password01', '홍길동', '010-1234-1111', 'user01@work.com	', '2019.04.05', 'G', 7500);

insert into member
values('user02', 'password02', '강감찬', '010-1234-1112', 'user02@work.com', '2019.05.06', 'G', 95000, null);

insert into member
values('user03', 'password03', '이순신', '010-1234-1113', 'user03@work.com', '2019.11.07', 'G', 3000, null);

insert into member
values('user04', 'password04', '김유신', '010-1234-1114', 'user04@work.com', '2020.12.08', 'S', null, '송중기');

insert into member
values('user05', 'password05', '유관순', '010-1234-1115', 'user05@work.com', '2021.05.09', 'A', null, null);

commit;

-- login
select * from member where member_id='user01' and member_pw='password01';

-- 내정보조회/상세조회
select * from member where member_id='user01';

-- 내정보변경 : 비밀번호, 이름, 휴대폰, 이메일
update member set member_pw='aa', name='bb', mobile='cc', email='dd' where member_id='user01';

-- 전체조회
select * from member;

-- 다중조건 테스트를 위한 레코드 추가 : 다중조건을 참고로해서 데이터 정보를 설정해서 추가하세요 
-- multiplwCondition init insert:



insert into member(member_id, member_pw, name, mobile, email, entry_date, grade, mileage)
values('test05', 'password01', '김길동', '010-2222-1111', 'test01@korea.com	', '2019.04.05', 'G', 7500);

insert into member
values('test02', 'password02', '박감찬', '010-2222-1112', 'test02@work.com', '2019.05.06', 'G', 99000, null);

insert into member
values('test03', 'password03', '이순들', '010-2222-1113', 'test03@work.com', '2019.11.07', 'S', null, '박우용');

insert into member
values('test04', 'password04', '김유진', '010-2222-1114', 'test04@work.com', '2020.12.08', 'S', null, '송중기');

insert into member
values('admin02', 'password05', '박형순', '010-2222-1115', 'test05@work.com', '2021.05.09', 'A', null, null);



insert into member(member_id, member_pw, name, mobile, email, entry_date, grade, mileage)
values('case01', 'password01', '이길동', '010-3333-1111', 'case01@korea.com	', '2019.04.05', 'G', 7500);

insert into member
values('case02', 'password02', '간감찬', '010-3333-1112', 'case02@work.com', '2019.05.06', 'G', 99000, null);

insert into member
values('case03', 'password03', '이순돌', '010-3333-1113', 'case03@work.com', '2019.11.07', 'S', null, '박우용');

insert into member
values('case04', 'password04', '정유진', '010-3333-1114', 'case04@work.com', '2020.12.08', 'S', null, '송중기');

insert into member
values('case05', 'password05', '이형진', '010-3333-1115', 'case05@work.com', '2021.05.09', 'A', null, null);



update member set email='user01@work.com' where member_id='user01';

commit;

select * from member;

DELETE FROM member WHERE member_id = 'test011';


-- 다중조건검색 sql 구문 
-- 아이디 
select * from member where member_id='user01';
-- 등급
select * from member where grade='G';
-- 마일리지 
select * from member where mileage >= 100000;
-- 담당자 
select * from member where manager='송중기';
-- 휴대폰: 뒷번호 4자리 
select substr(mobile,-4) from member;
select * from member where substr(mobile,-4) = '1111';
select * from member where mobile like '%1111';
-- 이메일 
select * from member where email='user01@work.com';

-- 이름
select * from member where name='김수정';

-- 아이디 + 이메일
select * from member where member_id='user01' or email='user01@work.com';

-- 변경 
update member set mileage=100000 where member_id='test02';
