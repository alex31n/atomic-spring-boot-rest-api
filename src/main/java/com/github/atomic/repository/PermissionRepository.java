package com.github.atomic.repository;

import com.github.atomic.model.entity.Permission;
import com.github.atomic.utils.Constants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PermissionRepository extends BaseRepository<Permission> {


    @Query(value = "select p.id, p.title from " + Constants.TABLE_PERMISSION + " p " +
            "left join " + Constants.TABLE_USER_PERMISSION + " up on p.id = up.permission_id " +
            "left join " + Constants.TABLE_USER + " u on up.user_id = u.id " +
            "where u.id = :userId", nativeQuery = true)
    List<Permission> findAllByUserId(@Param("userId") long userId);


    @Query(value = "select count(*) from permission p " +
            "inner join user_permission up on p.id = up.permission_id " +
            "where up.user_id = :userId and lower(p.title)= lower(:permissionTag) ",
            nativeQuery = true)

    int isPermissionAvailable(@Param("userId") long userId, @Param("permissionTag") String permissionTag);

}
