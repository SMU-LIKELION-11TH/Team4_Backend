package smu.likelion.Traditional.Market.domain.entity;

import lombok.*;
import smu.likelion.Traditional.Market.domain.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Store> stores = new ArrayList<>();

    @Column(name = "upload_filename")
    private String uploadFilename;

    @Column(name = "save_filename")
    private String saveFilename;

    @Builder
    public User(String email, String password, String nickname, Role role, String uploadFilename, String saveFilename) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.uploadFilename = uploadFilename;
        this.saveFilename = saveFilename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(String nickname, String uploadFilename, String saveFilename) {
        this.nickname = nickname;
        this.uploadFilename = uploadFilename;
        this.saveFilename = saveFilename;
    }
    public void update(String uploadFilename, String saveFilename) {
        this.uploadFilename = uploadFilename;
        this.saveFilename = saveFilename;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    public void addStore(Store store) { this.stores.add(store); }
}
