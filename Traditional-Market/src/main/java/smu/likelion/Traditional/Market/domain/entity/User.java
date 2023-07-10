package smu.likelion.Traditional.Market.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import smu.likelion.Traditional.Market.domain.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.NotNull;
import lombok.*;
import smu.likelion.Traditional.Market.domain.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;


    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Store> storeList = new ArrayList<>();

    @Builder
    public User(String email, String password, String nickname, Role role, String uploadFilename, String saveFilename) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    public void update(String nickname, String uploadFilename, String saveFilename) {
//        this.nickname = nickname;
//        this.uploadFilename = uploadFilename;
//        this.saveFilename = saveFilename;
//    }
//    public void update(String uploadFilename, String saveFilename) {
//        this.uploadFilename = uploadFilename;
//        this.saveFilename = saveFilename;
//    }
    public void update(String nickname) {
        this.nickname = nickname;
    }
    public void addReview(Review review) {
        this.reviews.add(review);
    }

}