package team21.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private Boolean anonymous;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<Like> likes = new ArrayList<>();

    public Comment(Boolean anonymous, String body, User user, Post post) {
        this.anonymous = anonymous;
        this.body = body;
        this.user = user;
        this.post = post;
    }
}
