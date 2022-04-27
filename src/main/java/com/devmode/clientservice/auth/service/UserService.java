package com.devmode.clientservice.auth.service;

import com.devmode.clientservice.auth.api.UserDto;
import com.devmode.clientservice.auth.api.UserInfo;
import com.devmode.clientservice.auth.dao.UserRepository;
import com.devmode.clientservice.auth.mapper.UserMapper;
import com.devmode.clientservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto save(UserInfo userInfo) {
        User user = userRepository.findFirstByExternalId(userInfo.getId())
                .orElseGet(() -> userRepository.save(userMapper.mapToModel(userInfo)));
        return userMapper.mapToDto(user);
    }

    public UserDto getByExternalId(String externalId) {
        return userRepository.findFirstByExternalId(externalId)
                .map(userMapper::mapToDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
