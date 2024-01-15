package com.site.portal.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>
{
	Optional<UserInfo> findByUserLoginId(String userLoginId);
}
