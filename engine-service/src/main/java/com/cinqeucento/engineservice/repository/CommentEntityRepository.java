package com.cinqeucento.engineservice.repository;

import com.cinqeucento.engineservice.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByTask_Id(Long taskId);

    @Modifying
    @Query(value = "update comments set comment_content=:content where comment_id=:id", nativeQuery = true)
    void updateById(@Param("id") Long id, @Param("content") String content);
}
