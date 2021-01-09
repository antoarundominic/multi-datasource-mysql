package com.example.multidatasourcemysql.repository.db2;

import com.example.multidatasourcemysql.model.db2.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<Comment, Integer> {
}
