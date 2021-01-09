package com.example.multidatasourcemysql.repository.db1;

import com.example.multidatasourcemysql.model.db1.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository
        extends JpaRepository<Post, Integer> {
}
