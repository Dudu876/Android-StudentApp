package com.example.dudu.studentsapp2.Model;

/**
 * Created by Michael on 3/25/2016.
 */

public class Student {
    String id;
    String name;
    String phone;
    String address;
    String birthDate;
    String birthTime;
    String imageName;
    boolean checked;

    public Student(String id, String name, String phone, String address, String birthDate, String birthTime, String imageName) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.imageName = imageName;
    }
    public Student() {
        this.id = "";
        this.name = "";
        this.phone = "";
        this.address = "";
        this.birthDate = "";
        this.birthTime = "";
        this.imageName = "image";
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
