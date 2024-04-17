# 채팅 만들기 (aaa)
insert into t_chat (chat_seq, last_chat, unread_cnt, maker_name, partner_name, partner_img)
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
INSERT INTO nc4taehyeon.t_user
(user_seq, is_active,
 last_login_date,
 profile_image_url,
 role,
 user_birth,
 user_id,
 user_name,
 user_pw,
 user_reg_date,
 user_tel)
VALUES (1,
        true,
        '2024-04-03 16:29:22.244150',
        null,
        'ROLE_USER',
        '2022-03-04 00:00:00.000000',
        'aaa',
        'aaa',
        '$2a$10$xCOoGzOmlnWGBp5YOZOgX.eaenpLuW.RJJC0uxGoS0VuKPBdfLhYe',
        '2024-04-03 14:55:16.652367',
        '010-4076-6673');
INSERT INTO nc4taehyeon.t_user
(user_seq, is_active, last_login_date, profile_image_url, role, user_birth, user_id, user_name, user_pw, user_reg_date,
 user_tel)
VALUES (2, true, '2024-04-03 14:55:43.707205', null, 'ROLE_USER', '2022-04-04 00:00:00.000000', 'bbb', 'bbb',
        '$2a$10$02WdaKImXStvPwK7f3tHDuQ0qEKNzQwZjTiR.dpZN.QiuhTmnQbPy', '2024-04-03 14:55:43.707205', '010-4076-6673');
