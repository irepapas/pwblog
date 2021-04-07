/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.entity;

import blog.dto.UserCreate;
import blog.dto.UserUpdate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Irene
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable{
    
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "user_sequence")
    protected Long id;
    public enum Role {
        ADMIN, USER
    }
    
    
    private String fName;
    @Column(nullable = false)
    private String lName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String pwd;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    private boolean bloccato = false;
    private boolean eliminato= false;

    public User() {
    }
    
    public User(UserCreate u) {
        this.fName = u.fName;
        this.lName = u.lName;
        this.email = u.email;
        this.pwd = u.pwd;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    
    public void setfName(UserUpdate u) {
        setfName(u.fName == null ? this.fName : u.fName);
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
     public void setlName(UserUpdate u) {
        setlName(u.lName == null ? this.lName : u.lName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
     public void setPwd(UserUpdate u) {
        setPwd(u.pwd == null ? this.pwd : u.pwd);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public boolean isBloccato(){
        return bloccato;
    }

    public void setBloccato(boolean bloccato) {
        this.bloccato = bloccato;
    }

    public boolean isEliminato() {
        return eliminato;
    }

    public void setEliminato(boolean eliminato) {
        this.eliminato = eliminato;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
