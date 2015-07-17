insert into Customer (id, username, password, name) values (1, 'bhp', 'bhp', 'Birkenhead Point')
insert into Customer (id, username, password, name) values (2, 'ian', 'ian', 'Ian')
insert into Customer (id, username, password, name) values (3, 'cathy', 'cathy', 'Cathy')
insert into Hotel (id, price, name, address, city, state, zip, country) values (1, 120, 'Marriott Courtyard', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (2, 180, 'Doubletree', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (3, 450, 'W Hotel', 'Union Square, Manhattan', 'NY', 'NY', '10011', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (4, 450, 'W Hotel', 'Lexington Ave, Manhattan', 'NY', 'NY', '10011', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (5, 250, 'Hotel Rouge', '1315 16th Street NW', 'Washington', 'DC', '20036', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (6, 300, '70 Park Avenue Hotel', '70 Park Avenue', 'NY', 'NY', '10011', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (8, 300, 'Conrad Miami', '1395 Brickell Ave', 'Miami', 'FL', '33131', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (9, 80, 'Sea Horse Inn', '2106 N Clairemont Ave', 'Eau Claire', 'WI', '54703', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (10, 90, 'Super 8 Eau Claire Campus Area', '1151 W Macarthur Ave', 'Eau Claire', 'WI', '54701', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (11, 160, 'Marriott Downtown', '55 Fourth Street', 'San Francisco', 'CA', '94103', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (12, 200, 'Hilton Diagonal Mar', 'Passeig del Taulat 262-264', 'Barcelona', 'Catalunya', '08019', 'Spain')
insert into Hotel (id, price, name, address, city, state, zip, country) values (13, 210, 'Hilton Tel Aviv', 'Independence Park', 'Tel Aviv', '', '63405', 'Israel')
insert into Hotel (id, price, name, address, city, state, zip, country) values (14, 240, 'InterContinental Tokyo Bay', 'Takeshiba Pier', 'Tokyo', '', '105', 'Japan')
insert into Hotel (id, price, name, address, city, state, zip, country) values (15, 130, 'Hotel Beaulac', ' Esplanade L�opold-Robert 2', 'Neuchatel', '', '2000', 'Switzerland')
insert into Hotel (id, price, name, address, city, state, zip, country) values (16, 140, 'Conrad Treasury Place', 'William & George Streets', 'Brisbane', 'QLD', '4001', 'Australia')
insert into Hotel (id, price, name, address, city, state, zip, country) values (17, 230, 'Ritz Carlton', '1228 Sherbrooke St', 'West Montreal', 'Quebec', 'H3G1H6', 'Canada')
insert into Hotel (id, price, name, address, city, state, zip, country) values (18, 460, 'Ritz Carlton', 'Peachtree Rd, Buckhead', 'Atlanta', 'GA', '30326', 'USA')
insert into Hotel (id, price, name, address, city, state, zip, country) values (19, 220, 'Swissotel', '68 Market Street', 'Sydney', 'NSW', '2000', 'Australia')
insert into Hotel (id, price, name, address, city, state, zip, country) values (20, 250, 'Meli� White House', 'Albany Street', 'Regents Park London', '', 'NW13UP', 'Great Britain')
insert into Hotel (id, price, name, address, city, state, zip, country) values (21, 210, 'Hotel Allegro', '171 West Randolph Street', 'Chicago', 'IL', '60601', 'USA')

insert into enum_domain(enum_domain_id, domain_name) values(1, 'Product Category')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(100, 'Unknown', 1)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(101, 'Top', 1)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(102, 'Pants', 1)

insert into enum_domain(enum_domain_id, domain_name) values(2, 'Company Category')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(200, 'Unknown', 2)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(201, 'Wholesaler', 2)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(202, 'Store', 2)

insert into enum_domain(enum_domain_id, domain_name) values(3, 'Gender')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(300, 'Unknown', 3)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(301, 'Male', 3)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(302, 'Female', 3)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(303, 'Unisex', 3)

insert into enum_domain(enum_domain_id, domain_name) values(4, 'Size')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(400, 'Unknown', 4)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(401, 'S', 4)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(402, 'M', 4)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(403, 'L', 4)

insert into enum_domain(enum_domain_id, domain_name) values(5, 'Color')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(500, 'Unknown', 5)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(501, 'Black', 5)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(502, 'White', 5)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(503, 'Gray', 5)

insert into enum_domain(enum_domain_id, domain_name) values(6, 'Invoice Status')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(600, 'Unknown', 6)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(601, 'Draft', 6)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(602, 'Completed', 6)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(603, 'Cancelled', 6)

insert into enum_domain(enum_domain_id, domain_name) values(7, 'Payment Type')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(700, 'Unknown', 7)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(701, 'Cash', 7)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(702, 'Card', 7)

insert into enum_domain(enum_domain_id, domain_name) values(8, 'Discount Unit')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(801, 'Amount', 8)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(802, 'Percentage', 8)

insert into enum_domain(enum_domain_id, domain_name) values(9, 'Journal Category')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(901, 'General', 9)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(902, 'Opening Balance', 9)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(903, 'Closing Balance', 9)

insert into enum_domain(enum_domain_id, domain_name) values(10, 'Product Type')
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(1001, 'Wholesale', 10)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(1002, 'Retail', 10)
insert into enum_instance(enum_instance_id, instance_name, domain_id) values(1003, 'Consignment', 10)

insert into company(company_id, company_name, category_instance_id) values(999, 'Unknown', 200)
insert into company(company_id, company_name, category_instance_id) values(1, 'ChloeC HQ', 202)
insert into company(company_id, company_name, category_instance_id) values(2, 'ChloeC Birkenhead Point', 202)
insert into company(company_id, company_name, category_instance_id) values(11, 'Desire Fashion', 201)
insert into company(company_id, company_name, category_instance_id) values(12, 'Indikah', 201)
insert into company(company_id, company_name, category_instance_id) values(13, 'Lovely Girl', 201)
insert into company(company_id, company_name, category_instance_id) values(14, 'Pink Diamond', 201)
insert into company(company_id, company_name, category_instance_id) values(15, 'So In Style', 201)
insert into company(company_id, company_name, category_instance_id) values(16, 'Spicy Sugar', 201)
insert into company(company_id, company_name, category_instance_id) values(17, 'Sunny Girl', 201)
insert into company(company_id, company_name, category_instance_id) values(18, 'Wakee', 201)

insert into label(label_id, label_name, company_id) values(999, 'Unknown', 999)
insert into label(label_id, label_name, company_id) values(1, 'Mia', 11)
insert into label(label_id, label_name, company_id) values(2, 'Indikah', 12)
insert into label(label_id, label_name, company_id) values(3, 'Oscar-St', 13)
insert into label(label_id, label_name, company_id) values(4, 'Pink Diamond', 14)
insert into label(label_id, label_name, company_id) values(5, 'So In Style', 15)
insert into label(label_id, label_name, company_id) values(6, 'Spicy Sugar', 16)
insert into label(label_id, label_name, company_id) values(7, 'Sunny Girl', 17)
insert into label(label_id, label_name, company_id) values(8, 'Wakee', 18)

insert into product(product_id, factory_barcode, factory_code, product_code, product_name, display_name, gender_instance_id, summary, description, wholesale_price, retail_price, category_instance_id, label_id, product_type_instance_id, creation_date, last_update_date, last_update_user_id) values (1,'2PDBBQ8D4TCY3X7T21','Test Factory Code 1', 'Test Product Code 1', 'Test Product Using Dr Prescription', 'Test Display Name 1', 301, 'Test Summary 1', 'Test Description 1', 50, 500, 101, 1, 1002, current_date(), current_date(), 1)
insert into product_option(product_option_id, option_key, option_value, product_id) values(1, 'color', 'Black', 1)
insert into product_option(product_option_id, option_key, option_value, product_id) values(2, 'gender', 'Male', 1)
insert into product_option(product_option_id, option_key, option_value, product_id) values(3, 'size', 'S/M', 1)

insert into product(product_id, factory_barcode, factory_code, product_code, product_name, display_name, gender_instance_id, summary, description, wholesale_price, retail_price, category_instance_id, label_id, product_type_instance_id, creation_date, last_update_date, last_update_user_id) values (2,4321,'Test Factory Code 2', 'Test Product Code 2', 'Test Product Name 2', 'Test Display Name 2', 302, 'Test Summary 2', 'Test Description 2', 70, 700, 102, 2, 1003, current_date(), current_date(), 1)
insert into product_option(product_option_id, option_key, option_value, product_id) values(4, 'color', 'White', 2)
insert into product_option(product_option_id, option_key, option_value, product_id) values(5, 'gender', 'Female', 2)
insert into product_option(product_option_id, option_key, option_value, product_id) values(6, 'size', 'M/L', 2)

insert into inventory_item(inventory_item_id,store_id,product_id,quantity) values(1, 1, 1, 10)
insert into inventory_item(inventory_item_id,store_id,product_id,quantity) values(2, 1, 2, 10)

insert into journal(journal_id, content, category_instance_id, creation_date, last_update_date, last_update_user_id) values(1, 'test', 901, current_date(), current_date(), 1)