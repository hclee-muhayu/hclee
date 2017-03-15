INSERT INTO user (id, user_ID, Password, Name, Email) VALUES (1, 'hello', '1234', '김동균', 'rainbow0616@naver.com');
INSERT INTO user (id, user_ID, PASSWORD, NAME, Email) VALUES (2, 'hello2', '1234', '김동균', 'rainbow0616@naver.com');

INSERT INTO question (id, contents, title, user_id, create_date)
VALUES (1, '안녕하세요? 테스트 입니다.', '테스트', 'hello', CURRENT_TIMESTAMP())