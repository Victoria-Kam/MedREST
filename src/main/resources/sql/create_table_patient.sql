create table patient(
      idpatient integer,
      fio varchar (250),
      dateofbith varchar(50),
      address varchar(150),
      idleave integer,
      identification_number varchar(14)
)

--Обновлени таблицы patient, добавление вторичного ключа idleave для связи с таблицей sick_leave
alter table patient add constraint idleave foreign key (idleave) references sick_leave (idleave);