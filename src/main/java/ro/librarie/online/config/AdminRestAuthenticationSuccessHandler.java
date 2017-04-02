package ro.librarie.online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ro.librarie.online.models.Admin;
import ro.librarie.online.repositories.AdminRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Component
public class AdminRestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private AdminRepository adminRepository;

    @Autowired
    public AdminRestAuthenticationSuccessHandler(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Admin admin = adminRepository.findByEmail(authentication.getName());

        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, admin);
    }

}
