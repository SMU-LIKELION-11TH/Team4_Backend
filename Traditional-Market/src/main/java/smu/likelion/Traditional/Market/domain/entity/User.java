package smu.likelion.Traditional.Market.domain.entity;

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
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "api_key")
    private String apiKey;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "upload_img", nullable = false)
    private String uploadImg;

    @Column(name = "save_img", nullable = false)
    private String saveImg;

    @Builder
    public User(String email, String password, String nickname, Role role, String uploadImg, String saveImg) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.uploadImg = uploadImg;
        this.saveImg = saveImg;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
