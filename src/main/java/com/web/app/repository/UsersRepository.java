package com.web.app.repository;

import com.web.app.entity.UsersEntity;
import com.web.app.model.SignUpRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * A {@link Repository} component, to interact with data in the "users" table.
 * <p>
 * <strong>NOTE:</strong> no implementation for this interface needed, a proxy over
 * {@link org.springframework.data.jpa.repository.support.SimpleJpaRepository} will be created,
 * including automatically generation of implementations of methods I wrote in this interface
 * ({@code Spring Data} "understands" what kind of {@code SQL/HQL/JPQL} query is needed by method's name)
 * <p>
 * Also note, that, by default, all methods in this interface and methods in
 * {@code SimpleJpaRepository} will be {@code transactional}, so, no need to annotate them with {@link Transactional}.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    /**
     * Finds a {@link UsersEntity} by the specified id.
     *
     * @param id specified id.
     * @return an {@link Optional<UsersEntity>} - a non-null wrapper of agenda
     * ({@code Spring Data} just requires this return-type).
     */
    Optional<UsersEntity> findById(Integer id);

    /**
     * Finds a {@code UsersEntity} by the specified username.
     *
     * @param username specified username.
     * @return user.
     * @see com.web.app.service.UsersService#loadUserByUsername(String).
     * @see com.web.app.security.UsersDetailsService#loadUserByUsername(String)
     */
    UsersEntity findByUsername(String username);

    /**
     * This method finds a {@code UsersEntity} instance, having specified username and email.
     *
     * @param email    specified email.
     * @param username specified username.
     * @return a user, having specified username and email.
     * @see com.web.app.service.impl.UsersServiceImpl#isUserValidForSaving(SignUpRequestDTO).
     */
    UsersEntity findByEmailAndUsername(String email, String username);

    /**
     * This method enables or disables a user, having specified username.
     * <p>
     * <strong>NOTE:</strong> it's good practice to annotate methods, annotated with {@link Modifying} annotation
     * with {@link Transactional} as well.
     * See 6.7.1: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactions
     *
     * @param toEnable specifies, should I enable or disable user.
     * @param username specified username.
     * @see com.web.app.service.impl.AdminServiceImpl#banUserByUsername(String)
     * @see com.web.app.service.impl.AdminServiceImpl#unBanUserByUsername(String)
     */
    @Modifying
    @Query(
            "UPDATE UsersEntity u SET enabled = ?1 WHERE u.username = ?2"
    )
    @Transactional
    void enableOrDisableUser(
            @Param("enabled") boolean toEnable,
            @Param("username") String username
    );
}