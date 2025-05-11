package com.core.TecHeart.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@JsonIgnoreProperties(value = {
        "password"}, allowSetters = true)
public class User implements Serializable {


    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空！")
    @Size(min = 8, message = "密码长度不能小于8！")
    private String password;

    private String authority;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
