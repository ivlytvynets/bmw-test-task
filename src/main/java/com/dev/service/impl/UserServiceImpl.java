package com.dev.service.impl;

import com.dev.model.User;
import com.dev.repository.AddressRepository;
import com.dev.repository.CompanyRepository;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<User> readUsers(String url) {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(url, User[].class);
        User[] users = responseEntity.getBody();
        if (Objects.isNull(users)) {
            return new ArrayList<>();
        }
        log.info("Status: {}; count of users: {}", responseEntity.getStatusCode(), responseEntity.getBody().length);
        return Arrays.asList(users);
    }

    @Override
    public void saveUsers(List<User> users) {
        users.forEach(u -> {
            u.setAddress(addressRepository.save(u.getAddress()));
            u.setCompany(companyRepository.save(u.getCompany()));
        });
        userRepository.saveAll(users);
    }

    @Override
    public List<User> validateUsersByEmail(List<User> users, String regex) {
        return users
                .parallelStream()
                .filter(u -> u.getEmail().matches(regex))
                .collect(Collectors.toList());
    }
}
