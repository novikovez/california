package org.california.service.user;

import org.california.config.UserInfoDetails;
import org.california.entity.user.UserInfo;
import org.california.dao.user.UserDao;
import org.california.enums.UserRole;
import org.california.exception.user.UserExistsException;
import org.california.exception.user.UserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("UserInfoService")
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userRepository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserInfo addUser(UserInfo userInfo) {
        for (String role : userInfo.getRole().split(",")) {
            if(!UserRole.exists(role)) {
                throw new UserRoleException("Роль пользователя не найдена:" + role);
            }
        }
        Optional<UserInfo> user = userRepository.findByEmail(userInfo.getEmail());
        if(user.isPresent()) {
            throw new UserExistsException("Пользователь с таким E-mail уже зарегистрирован!");
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        return userRepository.save(userInfo);
    }
}
