package com.example.jsoupexample;


import java.io.Serializable;

public class TableItem implements Serializable {

    private int id;

    private String dayName;

    private String owner;


    private int lessonNumber;

    private String lessonName;


    private String lessonRoom;


    private String lessonGroup;

    public TableItem() {
    }

    public TableItem(int id, String dayName, String owner, int lessonNumber, String lessonName, String lessonRoom, String lessonGroup) {
        this.id = id;
        this.dayName = dayName;
        this.owner = owner;
        this.lessonNumber = lessonNumber;
        this.lessonName = lessonName;
        this.lessonRoom = lessonRoom;
        this.lessonGroup = lessonGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(String lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    public String getLessonGroup() {
        return lessonGroup;
    }

    public void setLessonGroup(String lessonGroup) {
        this.lessonGroup = lessonGroup;
    }

    @Override
    public String toString() {
        return "TableItem{" +
                "dayName='" + dayName + '\'' +
                ", lessonNumber=" + lessonNumber +
                ", lessonName='" + lessonName + '\'' +
                ", lessonRoom='" + lessonRoom + '\'' +
                ", lessonGroup='" + lessonGroup + '\'' +
                '}';
    }
}
