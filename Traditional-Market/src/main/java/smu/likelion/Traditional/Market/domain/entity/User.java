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

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "upload_img", nullable = false)
    private String uploadImg;

    @Column(name = "store_img", nullable = false)
    private String storeImg;

    @Builder
    public User(String email, String password, String nickname, Role role, String uploadImg, String storeImg) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.uploadImg = uploadImg;
        this.storeImg = storeImg;
    }
}
