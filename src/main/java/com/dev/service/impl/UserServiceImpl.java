package com.dev.service.impl;

import com.dev.model.User;
import com.dev.repository.AddressRepository;
import com.dev.repository.CompanyRepository;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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
        User[] users = restTemplate.getForObject(url, User[].class);
        assert users != null;
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
