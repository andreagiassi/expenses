
INSERT INTO users (id, username, password, name, surname, gender)
 VALUES (1, 'andrea', 'Test!123', 'Andrea', 'Giassi', 0);
INSERT INTO users (id, username, password, name, surname, gender)
 VALUES (2, 'mario', 'Test!123', 'Mario', 'Rossi', 0);
INSERT INTO users (id, username, password, name, surname, gender)
 VALUES (3, 'Francesca', 'Test!123', 'Francesca', 'Rossi', 1);


INSERT INTO categories (category) VALUES ('Food');
INSERT INTO categories (category) VALUES ('Bills');
INSERT INTO categories (category) VALUES ('Transportation');
INSERT INTO categories (category) VALUES ('Home');
INSERT INTO categories (category) VALUES ('Car');
INSERT INTO categories (category) VALUES ('Shopping');
INSERT INTO categories (category) VALUES ('Clothing');
INSERT INTO categories (category) VALUES ('Insurance');
INSERT INTO categories (category) VALUES ('Tax');
INSERT INTO categories (category) VALUES ('Telephone');
INSERT INTO categories (category) VALUES ('Health');
INSERT INTO categories (category) VALUES ('Sport');
INSERT INTO categories (category) VALUES ('Baby');
INSERT INTO categories (category) VALUES ('Pet');
INSERT INTO categories (category) VALUES ('Beauty');
INSERT INTO categories (category) VALUES ('Electronics');
INSERT INTO categories (category) VALUES ('Gift');
INSERT INTO categories (category) VALUES ('Social');
INSERT INTO categories (category) VALUES ('Travel');
INSERT INTO categories (category) VALUES ('Education');
INSERT INTO categories (category) VALUES ('Fruits');
INSERT INTO categories (category) VALUES ('Office');
INSERT INTO categories (category) VALUES ('Hair');
INSERT INTO categories (category) VALUES ('Gardening');
INSERT INTO categories (category) VALUES ('Other');

INSERT INTO expenses (user_id, category_id, voice, price) VALUES (1, 3, 'bus ticket', 1.5);
INSERT INTO expenses (user_id, category_id, voice, price) VALUES (1, 1, 'pizza', 18.50);
INSERT INTO expenses (user_id, category_id, voice, price) VALUES (1, 17, 'small present', 35);
INSERT INTO expenses (user_id, category_id, voice, price) VALUES (1, 3, 'bus ticket', 1.5);

INSERT INTO expenses (user_id, category_id, voice, price) VALUES (2, 16, 'Samsung phone', 270);

INSERT INTO expenses (user_id, category_id, voice, price) VALUES (3, 18, 'Fb', 6);
INSERT INTO expenses (user_id, category_id, voice, price) VALUES (3, 24, 'Gym', 45);
