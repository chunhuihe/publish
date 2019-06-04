package com.gym.wechat.domain;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "coach_info")
public class tCoachInfo {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "token")
    private String token;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "gender")
    private int gender;
    @Column(name = "gym_name")
    private String gymName;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "record_date")
    private String recordDate;
    @Column(name = "vip_starttime")
    private String  vipStarttime;
    @Column(name = "vip_endtime")
    private String vipEndtime;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    public void insert(EntityManager em){
        em.persist(this);
    }
    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getVipStarttime() {
        return vipStarttime;
    }

    public void setVipStarttime(String vipStarttime) {
        this.vipStarttime = vipStarttime;
    }

    public String getVipEndtime() {
        return vipEndtime;
    }

    public void setVipEndtime(String vipEndtime) {
        this.vipEndtime = vipEndtime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


}
