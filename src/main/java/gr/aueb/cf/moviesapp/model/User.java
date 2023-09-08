package gr.aueb.cf.moviesapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FAV_USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Favorite> favorites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
