create table user_information (
    id int not null AUTO_INCREMENT PRIMARY key,
    username varchar(100) not null comment '用户名',
    password varchar(100) not null comment '密码',
    institute varchar(100) not null comment '学院',
    stu_number int(20) not null comment '学号',
    class_and_grades varchar(100) not null comment '班级+年级'
)comment = '用户信息表';