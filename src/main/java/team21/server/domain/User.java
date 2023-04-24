package team21.server.domain;

import lombok.Getter;
import lombok.Setter;
import team21.server.auth.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false, unique = true, length = 15)
    private String nickName;

    @Column(nullable = false)
    private String password;

    private String imageName;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
