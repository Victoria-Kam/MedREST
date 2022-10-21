select sickLeave.idleave as "№ листа",
       sickLeave.diagnosis as "Диагноз",
       sickLeave.treatment as "Лечение",
       sickLeave.dateopen as "Дата открытия",
       doc.fio as "Врач, открывший больничный лист",
       sickLeave.dateclose as "Дата открытия",
       doc.fio as "Врач, закрывший больничный лист"
from sick_leave sickLeave,doctor doc
where sickLeave.iddocopen =