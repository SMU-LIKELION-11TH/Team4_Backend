package smu.likelion.Traditional.Market.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smu.likelion.Traditional.Market.config.auth.AuthUser;
import smu.likelion.Traditional.Market.config.auth.AuthUtil;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.repository.UserRepository;

@Component
public class PermissionChecker {

    @Autowired
    UserRepository userRepository;

    public boolean checkPermission(Long userId){
        if(userId == findUser(AuthUtil.getAuthUser()).getId()){
            return true;
        }
        return false;
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.id(email, User.class.getName()));
    }
}
