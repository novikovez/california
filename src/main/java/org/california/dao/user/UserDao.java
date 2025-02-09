package org.california.dao.user;

import org.california.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
}
