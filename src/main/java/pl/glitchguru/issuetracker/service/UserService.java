package pl.glitchguru.issuetracker.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.glitchguru.issuetracker.controller.response.UserResponse;
import pl.glitchguru.issuetracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll().iterator())
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(UserResponse::from);
    }
}
