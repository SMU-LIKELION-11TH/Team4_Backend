package smu.likelion.Traditional.Market.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Store> storeList = new ArrayList<>();

    @Builder
    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}