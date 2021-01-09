package com.example.multidatasourcemysql.model.db2;

import com.example.multidatasourcemysql.model.db1.Post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private long id;

    @Column(name = "comment_body", nullable = false)
    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // getters
    public long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getCommentBody() {
        return commentBody;
    }
}
