-- ================ 카테고리
insert into categories(category_name, parent_id) values ('패션', 0);
insert into categories(category_name, parent_id) values ('가전/디지털', 0);
insert into categories(category_name, parent_id) values ('도서', 0);
insert into categories(category_name, parent_id) values ('식품', 0);

-- =============== Level 2 카테고리
-- 패션
insert into categories(category_name, parent_id) values ('여성', 1);
insert into categories(category_name, parent_id) values ('남성', 1);
insert into categories(category_name, parent_id) values ('아동', 1);;
insert into categories(category_name, parent_id) values ('스포츠', 1);;
insert into categories(category_name, parent_id) values ('잡화', 1);;

-- 가전
insert into categories(category_name, parent_id) values ('컴퓨터', 2);
insert into categories(category_name, parent_id) values ('냉장고', 2);
insert into categories(category_name, parent_id) values ('청소기', 2);
insert into categories(category_name, parent_id) values ('세탁기/건조기', 2);

-- 도서
insert into categories(category_name, parent_id) values ('여행', 3);
insert into categories(category_name, parent_id) values ('역사', 3);
insert into categories(category_name, parent_id) values ('예술', 3);
insert into categories(category_name, parent_id) values ('공학/과학', 3);

-- 식품
insert into categories(category_name, parent_id) values ('과일', 4);
insert into categories(category_name, parent_id) values ('채소', 4);
insert into categories(category_name, parent_id) values ('생수/음료', 4);
insert into categories(category_name, parent_id) values ('수산물', 4);
insert into categories(category_name, parent_id) values ('축산', 4);

-- =============== Level 3 카테고리행
-- === 패션
-- 여성
insert into categories(category_name, parent_id) values ('티', 5);
insert into categories(category_name, parent_id) values ('원피스', 5);
insert into categories(category_name, parent_id) values ('블라우스', 5);
insert into categories(category_name, parent_id) values ('바지/레깅스', 5);

-- 남성
insert into categories(category_name, parent_id) values ('티', 6);
insert into categories(category_name, parent_id) values ('맨투맨/후드', 6);
insert into categories(category_name, parent_id) values ('셔츠', 6);
insert into categories(category_name, parent_id) values ('바지/청바지', 6);

-- 아동
insert into categories(category_name, parent_id) values ('여아', 7);
insert into categories(category_name, parent_id) values ('남아', 7);
insert into categories(category_name, parent_id) values ('공용', 7);

-- 스포츠
insert into categories(category_name, parent_id) values ('여성', 8);
insert into categories(category_name, parent_id) values ('남성', 8);
insert into categories(category_name, parent_id) values ('유아', 8);

-- 신발/가방/잡화
insert into categories(category_name, parent_id) values ('시계', 9);
insert into categories(category_name, parent_id) values ('신발', 9);
insert into categories(category_name, parent_id) values ('가방', 9);
insert into categories(category_name, parent_id) values ('모자', 9);

-- === 가전
-- 컴퓨터
insert into categories(category_name, parent_id) values ('노트북', 10);
insert into categories(category_name, parent_id) values ('데스크탑', 10);
insert into categories(category_name, parent_id) values ('모니터', 10);
insert into categories(category_name, parent_id) values ('키보드/마우스', 10);
