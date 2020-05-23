package blog.develobeer.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@ToString
@Table(name="blog_post")
public class BlogPost {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seq;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer hits;

    @Column(name="board_id")
    private Integer boardID;

    @Column(name="is_delete")
    private boolean isDelete;

    @Column(name="modify_date")
    @UpdateTimestamp
    private Timestamp modifyDate;

    @Column(name="reg_date", updatable = false)
    @CreationTimestamp
    private Timestamp regDate;

}
