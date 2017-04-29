package ro.biblioteca.online.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.repositories.LibraryRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private LibraryRepository libraryRepository;

    @Autowired
    public RestAuthenticationSuccessHandler(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Library library = libraryRepository.findByEmail(authentication.getName());

        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, library);
    }

}
