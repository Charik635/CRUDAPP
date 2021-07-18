package web.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "длина имени должна быть от 2 до 30 символов")
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "Фамилия не может быть пустым")
    @Size(min = 2, max = 30, message = "длина фамилии должна быть от 2 до 30 символов")
    private String surName;
    @Column(name = "age")
    @Min(value = 0, message = "Возраст должен быть не менее 0")
    private int age;
    @Column(name="email")
    @Email(message = "Email не верный")
    @NotEmpty(message = "Поле не может быть пустым")
    private String email;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id, String name, String surName, int age, String email) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.email = email;
    }

    public User() {
    }
}
