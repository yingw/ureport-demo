package cn.wilmar.ureport.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @author yinguowei
 */
@Profile("dev")
@Component
public class UserDataLoader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(UserDataLoader.class);

    private final UserRepository userRepository;

    UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("UserDataLoader.run");
        Stream.of("Jojo", "Jon", "James", "Jack", "Jacky", "Jean", "Justin", "Judy", "Julia", "Jason", "Jane", "Joseph", "Jerry", "Jenny")
                .forEach(name -> userRepository.save(new User(name, name.toLowerCase() + "@sample.com")));
        userRepository.findAll().forEach(System.out::println);
    }
}
