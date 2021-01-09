package com.example.multidatasourcemysql.model.db1;

import com.example.multidatasourcemysql.model.db2.Comment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    private long id;

    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name = "post_attachment")
    private int postAttachment;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments;

    // setters

    public void setId(long id) {
        this.id = id;
    }

    public void setPostAttachment(int postAttachment) {
        this.postAttachment = postAttachment;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    // getters

    public long getId() {
        return id;
    }

    public int getPostAttachment() {
        return postAttachment;
    }

    public String getPostContent() {
        return postContent;
    }

}
