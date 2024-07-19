package dev.muhammad.learnsendingemails.service.impl;

import dev.muhammad.learnsendingemails.domain.Confirmation;
import dev.muhammad.learnsendingemails.domain.User;
import dev.muhammad.learnsendingemails.repository.ConfirmationRepository;
import dev.muhammad.learnsendingemails.repository.UserRepository;
import dev.muhammad.learnsendingemails.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;

    @Override
    public User saveUser(User user) {
        if (userRepository.existByEmail(user.getEmail())) {
            throw new RuntimeException("email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);

        confirmationRepository.save(confirmation);

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        return true;
    }
}
