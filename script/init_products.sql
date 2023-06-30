--  초기 아이템 설정
USE bar_menu;

# -- drop table
# -- To prevent a join conflict
# drop table if exists order_product;
# drop table if exists product_info;
#
# -- create table
# create table product_info (
#     product_id  varchar(255) not null
#         primary key,
#     mod_date    datetime(6)  not null,
#     reg_date    datetime(6)  not null,
#     description varchar(100) null,
#     image       varchar(100) null,
#     name        varchar(100) null,
#     price       bigint       null,
#     status      varchar(100) null,
#     stock       int          null,
#     category    varchar(100) null
# ) ;
#
# CREATE TABLE order_product
# (
#     order_product_id bigint      not null
#         auto_increment,
#     mod_date         datetime(6),
#     reg_date         datetime(6),
#     order_count      integer     not null,
#     order_product_amount integer not null,
#     roduct_id        bigint,
#     order_id         bigint,
#     CONSTRAINT pk_order_product PRIMARY KEY (order_product_id)
# );


-- insert table
#
#-- 탕
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (1,'탕',"description", '../img/곱도리탕.jpeg', '곱도리탕', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (2,'탕',"description", '../img/곱창묵은지전골.jpeg', '곱창묵은지전골', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (3,'탕',"description", '../img/나가사키탕.jpeg', '나가사키탕', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (4,'탕',"description", '../img/밀푀유나베.jpeg', '밀푀유나베', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (5,'탕',"description", '../img/새우탕.jpeg', '새우탕', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (6,'탕',"description", '../img/소불고기전골.jpeg', '소불고기전골', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (7,'탕',"description", '../img/어묵탕.jpeg', '어묵탕', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (8,'탕',"description", '../img/제주화산전골.jpeg', '제주화산전골', 15900,true,999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (9,'탕',"description", '../img/차돌박이 짬뽕탕.jpeg', '차돌박이 짬뽕탕', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (10,'탕',"description", '../img/차돌박이 김치찌개.jpeg', '차돌박이 김치찌개', 15900,true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (11,'탕',"description", '../img/크림짬뽕.jpeg', '크림짬뽕', 15900,true, 999, NOW(), NOW());
#
# -- 볶음
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (12,'볶음',"description", '../img/해물볶음우동.jpeg', '해물볶음우동', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (13,'볶음',"description", '../img/쭈꾸미볶음.jpeg', '쭈꾸미볶음', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (14,'볶음',"description", '../img/차돌박이 숙주볶음.jpeg', '차돌박이 숙주볶음', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (15,'볶음',"description", '../img/매콤무뼈닭발.jpeg', '매콤무뼈닭발', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (16,'볶음',"description", '../img/우삼겹 숙주볶음.jpeg', '우삼겹 숙주볶음', 15900, true, 999, NOW(), NOW());
#
# -- 별미
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (17,'별미',"description", '../img/신당동 명량떡볶이.jpeg', '신당동 명량떡볶이', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (18,'별미',"description", '../img/차돌냉채.jpeg', '차돌냉채', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (19,'별미',"description", '../img/국물 닭발.jpeg', '국물 닭발', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (20,'별미',"description", '../img/종로 육회 한 쌈.jpeg', '종로 육회 한 쌈', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (21,'별미',"description", '../img/담양숯불갈비.jpeg', '담양숯불갈비', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (22,'별미',"description", '../img/감바스.jpeg', '감바스', 15900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (23,'별미',"description", '../img/차돌박이 두부김치.jpeg', '차돌박이 두부김치', 15900, true, 999, NOW(), NOW());
#
# -- 샐러드
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (24,'샐러드',"description", '../img/차돌박이 부추샐러드.jpeg', '차돌박이 부추샐러드', 12900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (25,'샐러드',"description", '../img/과일샐러드.jpeg', '과일샐러드', 12900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (26,'샐러드',"description", '../img/생 연어 샐러드.jpeg', '생 연어 샐러드', 12900, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (27,'샐러드',"description", '../img/케이준 샐러드.jpeg', '케이준 샐러드', 12900, true, 999, NOW(), NOW());
#
# -- 음료
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (28,'음료',"description", '../img/코카콜라.jpeg', '코카콜라', 2500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (29,'음료',"description", '../img/웰치스[포도].jpeg', '웰치스[포도]', 2500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (30,'음료',"description", '../img/사이다.jpeg', '사이다', 2500, true, 999, NOW(), NOW());
#
# -- 주류
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (31,'주류',"description", '../img/진로.gif', '진로', 4500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (32,'주류',"description", '../img/처음처럼.jpeg', '처음처럼', 4500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (33,'주류',"description", '../img/참이슬.jpeg', '참이슬', 4500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (34,'주류',"description", '../img/테라.gif', '테라', 4500, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (35,'주류',"description", '../img/살얼음맥주.gif', '살얼음맥주 500ml', 4000, true, 999, NOW(), NOW());
insert into product_info(product_id, category, description, image, name, price, status, stock, reg_date, mod_date) values (36,'주류',"description", '../img/살얼음맥주.gif', '살얼음맥주 300ml', 3500, true, 999, NOW(), NOW());
#
