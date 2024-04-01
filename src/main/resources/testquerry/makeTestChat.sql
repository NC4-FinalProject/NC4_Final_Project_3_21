# 채팅 만들기 (aaa)
insert into t_chat (chat_seq, last_chat, unread_cnt, maker_id, partner_id, partner_img)
values (1, '안녕하세요', 1, 'aaa', 'bbb', 'img1.jpg'),
       (2, '안녕하세요', 2, 'aaa', 'robbie', 'img2.jpg'),
       (3, '안녕하세요', 3, 'aaa', 'robbie', 'img3.jpg'),
       (4, '안녕하세요', 4, 'aaa', 'robbie', 'img4.jpg'),
       (5, '안녕하세요', 5, 'bbb', 'robbie', 'img5.jpg'),
       (6, '안녕하세요', 6, 'bbb', 'robbie', 'img6.jpg'),
       (7, '안녕하세요', 7, 'bbb', 'robbie', 'img7.jpg'),
       (8, '안녕하세요', 8, 'bbb', 'robbie', 'img8.jpg'),
       (9, '안녕하세요', 9, 'bbb', 'robbie', 'img9.jpg'),
       (10, '안녕하세요', 10, 'bbb', 'aaa', 'img10.jpg');


# user 정보 입력
INSERT INTO nc4taehyeon.t_user (user_seq, birth, id, is_active, last_login_date, location, nickname, pw, reg_date, role, tel)
VALUES (1, '2021-04-05 00:00:00.000000', 'aaa', true, '2024-03-31 17:42:33.717384', '경기도 수원시', 'aaa', '$2a$10$GsPP2n5J0VNJmy5gtBH7yumGIzZu4zRojT9Ya32ks9/k9qaLzjbY2', '2024-03-31 17:42:33.717384', 'ROLE_USER', '010-4076-6673');
INSERT INTO nc4taehyeon.t_user (user_seq, birth, id, is_active, last_login_date, location, nickname, pw, reg_date, role, tel)
VALUES (2, '2022-04-04 00:00:00.000000', 'bbb', true, '2024-03-31 17:42:34.687695', '경기도 수원시', 'bbb', '$2a$10$cVW/GaOz28PgEb1lw2T5AegDA7jdExdJgJLRIpDYmSwdjG2Z/VBIy', '2024-03-31 17:42:34.687695', 'ROLE_USER', '010-4076-6673');

