package com.cinqeucento.engineservice.repository;

import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByProject_Id(Long id);

    Page<TaskEntity> findByTaskStatusAndProject(CurrentTaskStatus status, ProjectEntity project, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Tasks t set task_name=:taskName, " +
            "task_description=:taskDescription " +
            "where task_id =:id", nativeQuery = true)
    void updateById(@Param("id") Long id, @Param("taskName") String taskName,
                          @Param("taskDescription") String taskDescription);

    @Query("SELECT DISTINCT new com.cinqucento.engineservicestarter.dto.TaskEntityDTO (t.id, t.taskName, t.taskDescription, " +
            "t.taskStatus, t.createdAt, " +
            "t.lastStatusUpdate) " +
            "FROM TaskEntity t " +
            "JOIN UsersProjectRoleEntity upr ON t.project.id = upr.projectId " +
            "WHERE t.project.id = :projectId " +
            "AND upr.userId = :userId " +
            "AND t.taskStatus = :taskStatus " +
            "AND (upr.usersProjectRole IN :roles)")
    Page<TaskEntityDTO> findFilteredTasks(
                                        @Param("projectId") Long projectId,
                                        @Param("userId") Long userId,
                                        @Param("taskStatus") CurrentTaskStatus taskStatus,
                                        @Param("roles") List<UsersProjectRole> roles,
                                        Pageable pageable);
}
