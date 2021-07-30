alter table exam drop constraint FK__exam__resourse_i__37A5467C;

ALTER TABLE exam
  DROP COLUMN resourse_id;

ALTER TABLE exam
  ADD course_id int foreign key references course(id);