package com.gym.wechat.domain;

import javax.persistence.*;

@Entity
@Table(name = "userV2")
public class tCoach {
    /**
     * uuId : 17789114179
     * userId : 185112
     * type : phone
     * pwd : 701429
     * appId : 0
     */
    @Id
    @Column(name = "uu_id")
    private String uuId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "type")
    private String type;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "app_id")
    private int appId;
    public void insert(EntityManager em){
        em.persist(this);
    }
    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
