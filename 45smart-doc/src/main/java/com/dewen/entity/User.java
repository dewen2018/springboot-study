package com.dewen.entity;


/**
 * 用户信息
 *
 * @Title： demo
 * @Description： TODO
 * @Author： dewen
 * @Date： 2019/7/5 15:45
 */
public class User {

    /**
     * 主键id
     *
     * @required
     * @since 1.0
     */
    private int id;
    /**
     * 用户账号
     *
     * @required
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户年龄
     *
     * @ignore
     */
    private int age;
    /**
     * 用户爱好
     */
    private String hobby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
