package com.ortegapatriciaa.enventer;

import java.util.Date;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ortegapatriciaa on 7/29/2017.
 */

public class AccountInfo {


    String userId;
    String fullname;
    String emailAdd;
    String password;
    String birthday;
    String country;
    String mobileNum;



    // int user_type;
    //  Boolean client;
    // Boolean eventMember;
    // Boolean member;

    public AccountInfo(){

    }

    public AccountInfo(String userId, String fullname, String emailAdd, String password, String birthday, String country, String mobileNum) {

        this.userId = userId;
        this.fullname = fullname;
      //  this.lname = lname;
        this.emailAdd = emailAdd;
        this.password = password;
        this.birthday = birthday;
        this.country = country;
        this.mobileNum = mobileNum;
        //   this.user_type = user_type;
        //   this.client = client;
        //  this.eventMember = eventMember;
        // this.member = member;

    }


  public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

   /* public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }*/

   /* public Boolean getClient() {
        return client;
    }

    public void setClient(Boolean client) {
        this.client = client;
    }

   /* public Boolean getEventMember() {
        return eventMember;
    }

    public void setEventMember(Boolean eventMember) {
        this.eventMember = eventMember;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }*/

}
