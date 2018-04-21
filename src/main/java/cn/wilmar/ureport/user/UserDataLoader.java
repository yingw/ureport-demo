package cn.wilmar.ureport.user;

import cn.wilmar.ureport.report.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Jojo", "Jon", "James", "Jack", "Jacky", "Jean", "Justin", "Judy", "Julia", "Jason", "Justin", "Jane", "Joseph", "Jerry", "Jenny")
                .forEach(name -> userRepository.save(new User(name, name.toLowerCase() + "@sample.com")));
        userRepository.findAll().forEach(System.out::println);
    }
}
