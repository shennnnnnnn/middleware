package daoImpl;
import model.Student;
import dao.StudentDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;

public class StudentDaoImpl implements StudentDao{
    // 添加操作
    public void insert(Student s){
        String sql = "INSERT INTO student (id, name) values (?,?)";
        PreparedStatement state = null;
        Connect conn = null;
        //针对数据库的具体操作
        try{
            conn = new Connect();
            state = conn.getConnection().prepareStatement(sql);
            state.setLong(1,s.getID());
            state.setString(2,s.getName());

            state.executeUpdate();
            state.close();
            conn.close();
        }
        catch(Exception e){  }
    }

    //更新学生信息
    public void update(Student s){
        PreparedStatement state;
        try {
        Connect conn = new Connect();
        String sql = "UPDATE student SET name = ? WHERE id = ?";
        state = conn.getConnection().prepareStatement(sql);
        state.setLong(1,s.getID());
        state.setString(2,s.getName());
        state.execute();
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    //通过id删除
    public void delete(String iD){
        PreparedStatement state;
        try {
            Connect conn = new Connect();
            String sql = "DELETE FROM student WHERE id = ?";
            state = conn.getConnection().prepareStatement(sql);
            state.setLong(1, Long.parseLong(iD));
            state.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查找全部学生
    public List<Student> findAll(){
        List<Student> students = new ArrayList<>();
        PreparedStatement state;
        try {
            Connect conn = new Connect();
            String sql = "SELECT * FROM student";
            state = conn.getConnection().prepareStatement(sql);
            ResultSet resultSet = state.executeQuery();
            while(resultSet.next()) {
                students.add(new Student(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    //通过id查询
    public Student findByID(long iD){
        Student student = null;
        PreparedStatement state;
        try {
            Connect conn = new Connect();
            String sql = "SELECT * FROM student WHERE id = ?";
            state = conn.getConnection().prepareStatement(sql);
            state.setInt(1, (int) iD);
            ResultSet resultSet = state.executeQuery();
            resultSet.next();
            student = new Student(resultSet.getInt("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

}

