select *
from student
where age > 18
  and age < 21;

select name
from student;

select *
from student
where name like '%S%';

select *
from student
where age < id;

select *
from student
ORDER BY age;