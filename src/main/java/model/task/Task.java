package model.task;

import model.Default;
import model.category.Category;
import model.task.Repository;
import model.user.User;
import java.sql.Date;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;

public class Task extends Default {
    private String name;
    private Date deadLine;
    private String memo;
    private Boolean completed;
    private Integer userId;
    private Integer categoryId;



    public Task(Integer id, String name, Timestamp createdAt, Timestamp updatedAt, Date deadLine , String memo , Boolean completed , Integer userId ,  Integer categoryId) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.deadLine = deadLine;
        this.memo = memo;
        this.completed = completed;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    //getメソッド
    public String getName() {
        return name;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public String getMemo() {
        return memo;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    //setメソッド
    public void setName(String name) {
        this.name = name;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    public void insert() {
        //repositoryと連携
        Repository.insert(this);
    }

    public static ArrayList<Task> indexTasks(User user) {
        return Repository.indexTasks(user);
    }

}