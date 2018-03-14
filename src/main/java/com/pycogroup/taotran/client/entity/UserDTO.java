package com.pycogroup.taotran.client.entity;


import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
public class UserDTO extends AbstractEntity implements UserDetails {


    @NotNull
    @NotBlank
    @Column
    private String username;

    @Length(min = 8, max = 100)
    @Valid
    @Column
    private String password;

    @Min(10)
    @Max(100)
    @Column
    private int age;

    @Transient
    private List<Task> taskList;

    @Transient
    private List<? extends GrantedAuthority> grantedAuthorities;

    @Column
    private boolean accountNonExpired;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialsNonExpired;

    @Column
    private boolean enabled;


    public UserDTO() { // NOSONAR
        super();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public UserDTO(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.grantedAuthorities = builder.grantedAuthorities;
        this.age = builder.age;
        this.taskList = builder.taskList;
        this.accountNonExpired = builder.accountNonExpired;
        this.accountNonLocked = builder.accountNonLocked;
        this.credentialsNonExpired = builder.credentialsNonExpired;
        this.enabled = builder.enabled;

    }

    public UserDTO(String username, String password, List<? extends GrantedAuthority> grantedAuthorities) {
        this();
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public UserDTO(String username, String password, GrantedAuthority... authorities) {
        this(username, password, Arrays.asList(authorities));
    }

    public UserDTO(String username, String password) {
        this(username, password, new ArrayList<>());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setAuthorities(List<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public int getAge() {
        return age;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public static final class Builder {

        private String username;

        private String password;

        private int age;

        private List<Task> taskList;

        private List<? extends GrantedAuthority> grantedAuthorities;

        private boolean accountNonExpired;

        private boolean accountNonLocked;

        private boolean credentialsNonExpired;

        private boolean enabled;

        public Builder(String username, String password, List<? extends GrantedAuthority> grantedAuthorities) {
            this.username = username;
            this.password = password;
            this.grantedAuthorities = grantedAuthorities;
            this.accountNonExpired = true;
            this.accountNonLocked = true;
            this.credentialsNonExpired = true;
            this.enabled = true;
        }

        public Builder(String username, String password) {
            this(username, password, new ArrayList<>());
        }

        public Builder age(int value) {
            this.age = value;
            return this;
        }

        public Builder todoList(List<Task> value) {
            this.taskList = value;
            return this;
        }

        public Builder authorities(List<? extends GrantedAuthority> value) {
            this.grantedAuthorities = value;
            return this;
        }

        public Builder accountNonExpired(boolean value) {
            this.accountNonExpired = value;
            return this;
        }

        public Builder accountNonLocked(boolean value) {
            this.accountNonLocked = value;
            return this;
        }

        public Builder credentialsNonExpired(boolean value) {
            this.credentialsNonExpired = value;
            return this;
        }

        public Builder enabled(boolean value) {
            this.enabled = value;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }


    }
}
