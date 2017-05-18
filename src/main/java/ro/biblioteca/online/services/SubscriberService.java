package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Subscriber;
import ro.biblioteca.online.repositories.SubscriberRepository;

import java.util.List;

/**
 * Created by Alexandra Ale on 17/05/2017.
 */

@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> getAllSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findSubscribersByLibraryEmail(SecurityUtils.getCurrentLogin());

        subscribers.forEach(subscriber -> subscriber.setLibrary(null));

        return subscribers;
    }

    public List<Subscriber> getAllSubscribers(String firstName, String lastName) {
        List<Subscriber> subscribers = subscriberRepository.findSubscribersByLibraryEmailAndFirstNameAndLastName(SecurityUtils.getCurrentLogin(), firstName, lastName);

        subscribers.forEach(subscriber -> subscriber.setLibrary(null));

        return subscribers;
    }

}
