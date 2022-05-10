package dao;

import java.util.List;
import model.Student;

public interface StudentDao {
    public void insert(Student s);
    public void update(Student s);
    public void delete(String iD);
    public Student findByID(long iD);
    public List<Student> findAll();
}