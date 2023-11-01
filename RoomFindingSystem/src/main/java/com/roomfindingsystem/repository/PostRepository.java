package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    //BaoLTT
    //admin_dashboard
    @Query("select count(*) from PostEntity")
    int countPosts();
}
