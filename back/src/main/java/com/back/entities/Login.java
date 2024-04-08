package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Table;



import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity

public class Login implements UserDetails{
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;
public Integer getId(){ return id; }
public void setId(Integer o){ id=o; }
private String mail;
public String getMail(){ return mail; }
public void setMail(String o){ mail=o; }
private String mdp;
public String getMdp(){ return mdp; }
public void setMdp(String o){ mdp=o; }

    

    @Override
    public String getPassword() {
        return mdp;  
    }

    @Override
    public String getUsername() {
        return mail;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_Login"));
    }
}

