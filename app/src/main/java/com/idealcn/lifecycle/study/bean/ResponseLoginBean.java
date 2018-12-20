package com.idealcn.lifecycle.study.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class ResponseLoginBean   {



    /*

    var username : String,
                              var type : Int,
                              var token :String,
                              var password :String,
                              @Id var id :Long,
                              var icon : String,
                              var email :String
     */

    public String username;
    public int type;
    public String token;
    public String password;
    @Id
    public long id;
    public String icon;
    public String email;
    @Generated(hash = 1478740014)
    public ResponseLoginBean(String username, int type, String token,
            String password, long id, String icon, String email) {
        this.username = username;
        this.type = type;
        this.token = token;
        this.password = password;
        this.id = id;
        this.icon = icon;
        this.email = email;
    }
    @Generated(hash = 1032722625)
    public ResponseLoginBean() {
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
