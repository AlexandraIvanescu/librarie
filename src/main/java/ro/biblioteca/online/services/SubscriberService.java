package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.models.Subscriber;
import ro.biblioteca.online.repositories.LibraryRepository;
import ro.biblioteca.online.repositories.SubscriberRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandra Ale on 17/05/2017.
 */

@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;
    private LibraryRepository libraryRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository, LibraryRepository libraryRepository) {
        this.subscriberRepository = subscriberRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<Subscriber> getAllSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findSubscribersByLibraryEmail(SecurityUtils.getCurrentLogin());

        subscribers.forEach(subscriber -> {
            subscriber.setLibrary(null);
            subscriber.setBorrows(null);
        });

        return subscribers;
    }

    public List<Subscriber> getAllSubscribers(String firstName, String lastName) {
        List<Subscriber> subscribers = subscriberRepository.findSubscribersByLibraryEmailAndFirstNameAndLastName(SecurityUtils.getCurrentLogin(), firstName, lastName);

        subscribers.forEach(subscriber -> {
            subscriber.setLibrary(null);
            subscriber.setBorrows(null);
        });

        return subscribers;
    }

    public Subscriber getSubscriberById(int subscriberId) {
        Library library = libraryRepository.findByEmail(SecurityUtils.getCurrentLogin());

        Subscriber subscriber = subscriberRepository.findSubscriberByIdAndLibraryEmail(subscriberId, library.getEmail());

        subscriber.setLibrary(null);
        subscriber.setBorrows(null);

        return subscriber;
    }

    public boolean addSubscriber(Subscriber subscriber) {
        Library library = libraryRepository.findByEmail(SecurityUtils.getCurrentLogin());

        subscriber.setLibrary(library);

        subscriberRepository.saveAndFlush(subscriber);

        return true;
    }

    public boolean addPicture(MultipartFile picture) {
        String rootDirectory = System.getProperty("user.dir") + "/src/main/webapp/images/subscribers/";
        File newFile = new File(rootDirectory + picture.getOriginalFilename());

        try {
            picture.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean deleteSubscriber(Subscriber subscriber) {

        subscriberRepository.delete(subscriber);

        return true;
    }

}
