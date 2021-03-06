package model.task;

import lib.mysql.Client;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;

public class Repository extends Client {
    public static void insert(Task task) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String sql = "insert into tasks (name, created_at, updated_at, dead_line, memo, completed, user_id ,category_id) values (?, ?, ?, ?, ?, ?, ?, ?)";

            connection = create();

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, task.getName());
            stmt.setTimestamp(2, currentTime);
            stmt.setTimestamp(3, currentTime);
            stmt.setDate(4, task.getDeadLine());
            stmt.setString(5, task.getMemo());
            stmt.setBoolean(6, task.getCompleted());
            stmt.setInt(7, task.getUserId());
            stmt.setInt(8, task.getCategoryId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }

    public static ArrayList<Task> indexTasks(User user) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from tasks where user_id = ? order by category_id,dead_line";

            connection = create();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();

            ArrayList<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        null,
                        null,
                        rs.getDate("dead_line"),
                        rs.getString("memo"),
                        rs.getBoolean("completed"),
                        rs.getInt("category_id"),
                        rs.getInt("user_id")
                );
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, stmt, rs);
        }

    }

    public static Task search(Task task){
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from tasks where id = ? order by category_id,`dead_line`";

            connection = create();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, task.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getDate("dead_line"),
                        rs.getString("memo"),
                        null,
                        rs.getInt("user_id"),
                        rs.getInt("category_id")
                );
            }
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, stmt, rs);
        }
    }

    public static void update(Task task){
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String sql = "update tasks set name = ?, updated_at = ?, dead_line = ?, memo = ?, user_id = ?, category_id = ? where id = ?";

            connection = create();

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, task.getName());
            stmt.setTimestamp(2, currentTime);
            stmt.setDate(3, task.getDeadLine());
            stmt.setString(4, task.getMemo());
            stmt.setInt(5,task.getUserId());
            stmt.setInt(6,task.getCategoryId());
            stmt.setInt(7,task.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}