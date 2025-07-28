package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.UserCreateDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.mapper.UserMapper;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO create(UserCreateDTO dto) {
        User user = UserMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserMapper.toUserDTO(savedUser);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::toUserDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Can't delete user not found ");
        }
        userRepository.deleteById(id);

    }
}
