package com.example.appointmentapplication;

public class User {


    public String name;
    public String mobileno;
    public String date;
    public String time;

    public String email;
    public String reason;
    public String visitor;



        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

    public User(String name, String mobileno, String date, String time, String email, String reason, String visitor) {
        this.name = name;
        this.mobileno = mobileno;
        this.date = date;
        this.time = time;
        this.email = email;
        this.reason = reason;
        this.visitor = visitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

}
