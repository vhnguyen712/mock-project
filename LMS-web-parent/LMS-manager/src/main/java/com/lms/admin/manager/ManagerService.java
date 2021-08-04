package com.lms.admin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.commom.entity.Manager;
import com.lms.commom.entity.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Service
@Transactional
public class ManagerService {

    private static final int MANAGER_PER_PAGE = 3;
    @Autowired
    ManagerRepository managerRepository;

    public List<Manager> getListUsers() {
        return (List<Manager>) managerRepository.findAll();
    }

    public Page<Manager> listByPage(int pageNum, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, MANAGER_PER_PAGE);

        if (keyword != null) {
            return managerRepository.findAll(keyword, pageable);
        }

        return managerRepository.findAll(pageable);
    }

    public void updateEnable(Integer id, boolean enable) {
        managerRepository.enableManager(id, enable);
    }

    public void delete(int id) {
        managerRepository.deleteById(id);
    }

    public Manager getManagerById(int id) {
        return managerRepository.findById(id).get();
    }

    public boolean checkAuthenticationType(HttpServletRequest request) {
        Object principal = request.getUserPrincipal();
        boolean check = true;
        if (principal instanceof UsernamePasswordAuthenticationToken || principal instanceof RememberMeAuthenticationToken) {
            check = true;
        } else if (principal instanceof OAuth2AuthenticationToken) {
            check = false;
        }
        return check;
    }
    
    public void updateUserProfile(Manager manager) {
        managerRepository.updateProfile(manager.getName(), manager.getId());
    }
    
    public void changePassword(String password, int id) {
        managerRepository.changePassword(password, id);
    }
}
