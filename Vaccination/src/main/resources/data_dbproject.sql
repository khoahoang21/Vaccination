
use dbproject;
select * from vaccine;
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Live attenuated","a","VT00001");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Killed whole organism","a","VT00002");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Toxoid","a","VT00003");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Subunit","a","VT00004");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Virus-like praticle","a","VT00005");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Outer Membernay","a","VT00006");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Protein-polysaccharide conjugate","a","VT00007");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Viral vectored","a","VT00008");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Nuclecic acid vaccine","a","VT00009");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Bacterial vectored","a","VT00010");
INSERT INTO vaccine_types(description, status, vaccine_type_name, image, vaccine_type_id) 
VALUES ("Type of Vaccine",true,"Antigen-Presenting cell","a","VT00011");
-- SELECT * FROM vaccine  TYPE

INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00001","","","",10,"USA",true,"2023-10-29","2023-12-29","Measles","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00002","","","",10,"USA",true,"2023-12-29","2024-01-09","MUMPS","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00003","","","",19,"USA",true,"2023-08-15","2023-12-29","Rubella","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00004","","","",15,"USA",true,"2023-01-29","2023-12-29","Yellow Fever","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00005","","","",16,"China",true,"2023-06-29","2023-12-29","Infuenza","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00006","","","",10,"Japan",true,"2023-01-29","2023-12-29","Japanese Encephalitis","VT00001");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00007","","","",10,"China",true,"2023-02-28","2023-06-29","Whole-cell pertussis","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00008","","","",10,"Japan",true,"2023-01-29","2023-05-29","Polio","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00009","","","",10,"China",true,"2023-07-29","2023-10-29","Influeza","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00010","","","",10,"Japan",true,"2023-10-29","2023-12-29","Japanese Encephalitis","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00011","","","",10,"USA",true,"2023-04-29","2023-12-29","Hepatitis A","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00012","","","",100,"USA",true,"2023-03-29","2023-06-29","Rabies","VT00002");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00013","","","",10,"USA",true,"2023-02-28","2023-10-29","Diphtheria","VT00003");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00014","","","",20,"USA",true,"2023-03-29","2023-09-29","Tetanus","VT00003");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00015","","","",30,"USA",true,"2023-11-29","2023-10-29","Pertussis","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00016","","","",40,"Korea",true,"2024-02-28","2023-12-29","Influeza","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00017","","","",35,"Japan",true,"2023-05-29","2023-07-29","Hepatitis B","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00018","","","",25,"USA",true,"2023-01-29","2023-05-29","Meningococcal","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00019","","","",15,"Moderna",true,"2023-10-29","2024-12-29","Pneumococcal","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00020","","","",26,"Moderna",true,"2023-10-29","2024-01-29","Typhoid","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00021","","","",24,"China",true,"2023-10-29","2024-10-29","Hepatitis A","VT00004");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00022","","","",45,"USA",true,"2023-01-29","2024-01-29","Human Papillomavirus","VT00005");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00023","","","",50,"USA",true,"2023-01-29","2023-08-29","Group B Meningococcal","VT00006");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00024","","","",55,"Japan",true,"2023-08-29","2024-04-29","Hoemophilus Infuenzae type B","VT00007");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00025","","","",51,"Moderna",true,"2023-04-29","2023-07-29","Pneumococcal","VT00007");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00026","","","",17,"USA",true,"2023-04-29","2023-10-29","Menigococcal","VT00007");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00027","","","",12,"China",true,"2023-04-29","2023-09-29","Typhoid","VT00007");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00028","","","",41,"Canada",true,"2023-01-29","2023-04-29","Ebola","VT00008");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00029","","","",44,"USA",true,"2023-01-29","2023-12-29","SARS-CoV-2","VT00009");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00030","","","",10,"USA",true,"2023-10-29","2023-12-29","Experimental","VT00010");
INSERT INTO vaccine(vaccine_id,contraindication,description,indication,number_of_injection, origin, status,time_begin_next_injection, time_end_next_injection, vaccine_name, vaccine_type_id) 
VALUES ("00031","","","",36,"China",true,"2023-10-29","2023-12-29","Experimental","VT00011");
-- SELECT * FROM VACCINE

INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Tay Ninh","1999-01-01","a1@gmail.com","Tom",true,"209273615810","1","0928362701","a1");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Ca Mau","2000-01-01","a2@gmail.com","Jessica",true,"209273615811","1","0928362702","a2");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Vung Tau","1998-01-01","a3@gmail.com","Jenifer",true,"209273615812","1","0928362703","a3");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("TP Ho Chi Minh","1997-01-01","a4@gmail.com","Bao",true,"209273615813","1","0928362704","a4");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Ha Noi","1994-01-01","a5@gmail.com","Anh Minh",true,"209273615814","1","0928362705","a5");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Nghe An","1995-01-01","a6@gmail.com","Tien",true,"209273615815","1","0928362706","a6");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Thanh Hoa","1996-01-01","a7@gmail.com","Cuong",true,"209273615816","1","0928362707","a7");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Can Tho","2002-01-01","a8@gmail.com","Khoa",true,"209273615817","1","0928362708","a8");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Da Nang","2001-01-01","a9@gmail.com","Thai Duy",true,"209273615818","1","0928362709","a9");
INSERT INTO customers(address,date_of_birth,email,full_name,gender,indentify_card,password,phone,username) VALUES ("Thai Nguyen","2003-01-01","a10@gmail.com","tdi",true,"209273615819","1","0928362710","a10");
-- SELECT * FROM customers

INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-10",2,"00001");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-01-20","Normal","HN","2023-01-10",3,"00002");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","TNinh","2023-07-10",2,"00003");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-12-15","Normal","Thai Nguyen","2023-11-10",1,"00001");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2022-11-15","Normal","HCM","2022-10-10",3,"00004");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HN","2023-04-01",2,"00002");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2024-11-15","Normal","QTri","2023-10-10",2,"00005");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2021-05-15","Normal","CM","2021-03-10",3,"00001");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2024-11-15","Normal","CT","2023-12-10",1,"00006");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-10",2,"00004");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-10-15","Normal","HN","2023-10-10",3,"00011");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-05",2,"00012");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2021-09-15","Normal","CM","2021-03-10",3,"00011");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2024-01-15","Normal","CT","2023-12-10",1,"00008");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-10",2,"00008");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-10-15","Normal","HN","2023-10-10",3,"00010");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-12-15","Normal","HCM","2023-10-05",2,"00009");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2024-01-15","Normal","CT","2023-12-10",1,"00005");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-10",2,"00006");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-10-15","Normal","HN","2023-10-10",3,"00007");
INSERT INTO injection_schedules(end_date,note,place,start_date,status,vaccine_id) VALUES ("2023-11-15","Normal","HCM","2023-10-05",2,"00003");

-- select * from schedule


INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-01-21","HCM","2023-02-2",1,"haha",1,"00001",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-01-21","HCM","2023-02-2",1,"haha",2,"00002",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HN","2023-11-2",1,"haha",3,"00003",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","TNinh","2023-11-2",1,"haha",4,"00004",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","Tay Nguyen","2023-11-2",1,"haha",5,"00005",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","CM","2023-11-2",1,"haha",6,"00006",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",7,"00007",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",8,"00008",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",9,"00009",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",10,"00010",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",1,"00002",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-11-2",1,"haha",2,"00002",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-10-21","HCM","2023-12-2",1,"normal",3,"00004",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-09-21","HCM","2023-10-2",1,"normal",3,"00003",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-08-21","HCM","2023-09-2",1,"allergy",4,"00006",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-07-21","HCM","2023-09-2",1,"normal",4,"00007",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-06-21","HCM","2023-08-2",1,"normal",5,"00009",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-05-21","HCM","2023-06-2",1,"normal",5,"00008",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-08-21","HCM","2023-09-2",1,"normal",6,"00004",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-09-21","HCM","2023-12-2",1,"allergy",6,"00005",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-07-21","HCM","2023-09-2",1,"allergy",10,"00001",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-06-21","HCM","2023-08-2",1,"allergy",9,"00006",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-01-21","HCM","2023-04-2",1,"allergy",8,"00011",true);
INSERT INTO injection_results(injection_date,injection_place,next_injection_date,number_of_injection,prevention,customer_id,vaccine_id,status) 
VALUES ("2023-03-21","HCM","2023-05-2",1,"allergy",7,"00012",true);

-- SELECT * FROM injection_results