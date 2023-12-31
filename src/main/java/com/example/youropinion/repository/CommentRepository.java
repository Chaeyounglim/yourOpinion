package com.example.youropinion.repository;

import com.example.youropinion.entity.Comment;
import com.example.youropinion.entity.Post;
import com.example.youropinion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByCreatedAtAsc(long id);

    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);
}
