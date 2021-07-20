package web.model;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
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

    @Column(name = "username")
    @Email(message = "Email не верный")
    @NotEmpty(message = "Поле не может быть пустым")
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "password")
    private String password;

    private Boolean isAdmin;

    private Boolean isUser;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    public void takeRole(Role role) {
        roles.add(role);
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public User(int id, String name, String surName, int age, String username, String passWord) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.username = username;
        this.password = passWord;
    }

    public User() {
    }

    public User(int id, String name, String surName, int age, String username, Set<Role> roles, String passWord) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.username = username;
        this.roles = roles;
        this.password = passWord;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }


    public void setPassword(String passWord) {
        this.password = passWord;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
