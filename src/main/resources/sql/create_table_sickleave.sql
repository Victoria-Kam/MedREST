create table sick_leave(
   idleave integer,
   diagnosis varchar (50),
   treatment oid,
   dateopen varchar(50),
   iddocopen integer,
   dateend varchar(50),
   iddocend integer
)


--Обновлени таблицы sick_leave, добавление вторичного ключа iddocopen (доктор, открывший больничный) для связи с таблицей iddoctor
alter table sick_leave add constraint iddocopen foreign key (iddocopen) references doctor (iddoctor);
--Обновлени таблицы sick_leave, добавление вторичного ключа iddocend (доктор, закрывший больничный) для связи с таблицей iddoctor
alter table sick_leave add constraint iddocend foreign key (iddocend) references doctor (iddoctor);

alter table sick_leave  add constraint idpatient foreign key (idpatient) references patient (idpatient);