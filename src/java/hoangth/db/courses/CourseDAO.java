/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangth.db.courses;

import hoangth.Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ACER
 */
public class CourseDAO {

    public List<CourseDTO> selectAll() throws SQLException {
        List<CourseDTO> list = null;
        Connection con = DBContext.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Course where status = 1 ");
        list = new ArrayList<>();
        while (rs.next()) {
            CourseDTO course = new CourseDTO();
            course.setCouId(rs.getString("couId"));
            course.setCouName(rs.getString("couName"));
            course.setImage(rs.getString("image"));
            course.setFee(rs.getDouble("fee"));
            course.setCaId(rs.getInt("caId"));
            course.setStartDate(rs.getDate("startDate"));
            course.setEndDate(rs.getDate("endDate"));
            course.setLastUpdateDate(rs.getDate("lastUpdateDate"));
            course.setLastUpdateUser(rs.getString("lastUpdateUser"));
            list.add(course);
        }
        con.close();

        return list;
    }
    
    public List<CourseDTO> selectInactive() throws SQLException {
        List<CourseDTO> list = null;
        Connection con = DBContext.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Course where status = 0 ");
        list = new ArrayList<>();
        int i = 0;
        while (rs.next()) {
            CourseDTO course = new CourseDTO();
            course.setCouId(rs.getString("couId"));
            course.setCouName(rs.getString("couName"));
            course.setImage(rs.getString("image"));
            course.setFee(rs.getDouble("fee"));
            course.setCaId(rs.getInt("caId"));
            course.setStartDate(rs.getDate("startDate"));
            course.setEndDate(rs.getDate("endDate"));
            course.setLastUpdateDate(rs.getDate("lastUpdateDate"));
            course.setLastUpdateUser(rs.getString("lastUpdateUser"));
            list.add(course);
            i++;
        }
        con.close();
        if (i==0){
            return null;
        }
        return list;
    }

    public List<CourseDTO> selectType(int caId) throws SQLException {
        List<CourseDTO> list = null;
        Connection con = DBContext.getConnection();

        PreparedStatement stm = con.prepareStatement("Select * from Course where ( CaId = ? AND status = 1)");
        stm.setInt(1, caId);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            CourseDTO course = new CourseDTO();
            course.setCouId(rs.getString("couId"));
            course.setCouName(rs.getString("couName"));
            course.setImage(rs.getString("image"));
            course.setFee(rs.getDouble("fee"));
            course.setCaId(rs.getInt("caId"));
            course.setStartDate(rs.getDate("startDate"));
            course.setEndDate(rs.getDate("endDate"));
            course.setLastUpdateDate(rs.getDate("lastUpdateDate"));
            course.setLastUpdateUser(rs.getString("lastUpdateUser"));
            list.add(course);
        }
        return list;
    }

    public List<CourseDTO> search(String search) throws SQLException {
        List<CourseDTO> list = null;
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("select * from Course where (couName like ? AND status = 1)  ORDER BY [startDate] DESC");
        stm.setString(1, "%" + search + "%");
        ResultSet rs = stm.executeQuery();
        CourseDTO course = new CourseDTO();
        int i = 0;
        list = new ArrayList<>();
        while (rs.next()) {
            course = new CourseDTO();
            course.setCouId(rs.getString("couId"));
            course.setCouName(rs.getString("couName"));
            course.setImage(rs.getString("image"));
            course.setFee(rs.getDouble("fee"));
            course.setCaId(rs.getInt("caId"));
            course.setStartDate(rs.getDate("startDate"));
            course.setEndDate(rs.getDate("endDate"));
            course.setLastUpdateDate(rs.getDate("lastUpdateDate"));
            course.setLastUpdateUser(rs.getString("lastUpdateUser"));
            list.add(course);
            i++;
        }
        if (i == 0) {
            return null;
        } else {
            return list;
        }
    }

    public List<CourseDTO> searchPage(String search, int page) throws SQLException {
        List<CourseDTO> list = null;
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("select * from Course where (couName like ? AND status = 1)  ORDER BY [startDate] DESC");
        stm.setString(1, "%" + search + "%");
        ResultSet rs = stm.executeQuery();
        CourseDTO course = new CourseDTO();
        int i = 0;
        int x = 0;
        list = new ArrayList<>();
        while (rs.next()) {
            if (x <= 20 * page - 1 && x >= 20 * (page - 1)) {
                course = new CourseDTO();
                course.setCouId(rs.getString("couId"));
                course.setCouName(rs.getString("couName"));
                course.setImage(rs.getString("image"));
                course.setFee(rs.getDouble("fee"));
                course.setCaId(rs.getInt("caId"));
                list.add(course);
                i++;
            }
            x++;

        }
        if (i == 0) {
            return null;
        } else {
            return list;
        }
    }

    public CourseDTO find(String id) throws SQLException {
        Connection con = DBContext.getConnection();
        CourseDTO course = null;
        String sql = "select p.*, c.caName as category "
                + " from course p join category c on p.CaId=c.CaId where p.courseId= ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            course = new CourseDTO();
            course.setCouId(rs.getString("couId"));
            course.setCouName(rs.getString("couName"));
            course.setImage(rs.getString("image"));
            course.setFee(rs.getDouble("fee"));
            course.setCaId(rs.getInt("caId"));
            course.setStartDate(rs.getDate("startDate"));
            course.setEndDate(rs.getDate("endDate"));
            course.setLastUpdateDate(rs.getDate("lastUpdateDate"));
            course.setLastUpdateUser(rs.getString("lastUpdateUser"));
        }
        con.close();

        return course;
    }
    
    public String newId() throws SQLException{
        Connection con = DBContext.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Course where status = 1 ");
        int i = 0;
        while (rs.next()) {
            i++;
        }
        i++;
        String newId = "C" + i;
        rs = stm.executeQuery("select * from Course where status = "+ newId);
        while (rs.next()) {
            i++;
            newId = "C" + i;
            rs = stm.executeQuery("select * from Course where status = "+ newId);
        }
        return newId;
    }

    public void update(String user, String couId, String couName, double fee, int caId, Date startDate, Date endDate) throws SQLException {
        Connection con = DBContext.getConnection();
         System.out.println("Update 1.1");
        String sql = "update Course set couName = ?, fee = ?, caId = ?, startDate = ?, endDate = ?,  lastUpdateDate = ? , lastUpdateUser = ?  "
                + " where couId = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        System.out.println("Update 1.2");
        stm.setString(1, couName);
        stm.setDouble(2, fee);
        stm.setInt(3, caId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(4, sdf.format(startDate));
        stm.setString(5, sdf.format(endDate));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        stm.setString(6, dtf.format(now));
        stm.setString(7, user);
        stm.setString(8, couId);
         System.out.println("Update 1.3");
        stm.executeUpdate();
         System.out.println("Update 1.4");
        con.close();
    }

    public void deactivate(String couId) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update Course set [status] = 0 "
                + " where couId = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, couId);
        stm.executeUpdate();
        con.close();
    }

    public void activate(String couId) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update Course set [status] = 1 "
                + " where couId = ?";
        PreparedStatement stm = con.prepareStatement(sql);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
        stm.setString(1, couId);
        stm.executeUpdate();
        con.close();
    }

    public void delete(String id) throws SQLException {
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("delete Course where couId = ?");
        stm.setString(1, id);
        stm.executeUpdate();
        con.close();
    }
}
