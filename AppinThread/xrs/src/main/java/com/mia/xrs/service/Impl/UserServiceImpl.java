package com.mia.xrs.service.Impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.Department;
import com.mia.xrs.entity.Role;
import com.mia.xrs.entity.User;
import com.mia.xrs.entity.UserSpecification;
import com.mia.xrs.exception.NotFoundException;
import com.mia.xrs.repository.DepartmentRepository;
import com.mia.xrs.repository.RoleRepository;
import com.mia.xrs.repository.UserRepository;
import com.mia.xrs.repository.UserSpecificationRepository;
import com.mia.xrs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSpecificationRepository userSpecificationRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public UserDto save(UserDto userDto) {

//        Role roleAdmin = roleRepository.save(Role.builder().authority("ROLE_ADMIN").build());
        Role roleUser = roleRepository.save(Role.builder().authority("ROLE_USER").build());

        UserSpecification userSpecification = new UserSpecification();
        userSpecification.setFirstName(userDto.getFirstName());
        userSpecification.setLastName(userDto.getLastName());
        userSpecification.setFatherName(userDto.getFatherName());
        userSpecification.setPoliceCard(userDto.getPoliceCard());
        userSpecification.setRank(userDto.getRank());

        Department department = departmentRepository.findById(userDto.getDepartment().getId())
                .orElseThrow(() -> new NotFoundException("Department by id : " + userDto.getDepartment().getId() + " not found"));

        userSpecification.setDepartment(department);
        userSpecificationRepository.save(userSpecification);

        User save = userRepository.save(User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .isEnabled(true)
                .authorities(List.of(roleUser))
                .userSpecification(userSpecification)
                .build());

        return UserDto.builder()
                .id(save.getId())
                .username(save.getUsername())
                .password(save.getPassword())
                .authorities(save.getAuthorities().toString())
                .firstName(save.getUserSpecification().getFirstName())
                .lastName(save.getUserSpecification().getLastName())
                .fatherName(save.getUserSpecification().getFatherName())
                .policeCard(save.getUserSpecification().getPoliceCard())
                .rank(save.getUserSpecification().getRank())
                .department(DepartmentDto.builder()
                        .id(save.getUserSpecification().getDepartment().getId())
                        .name(save.getUserSpecification().getDepartment().getName())
                        .parentId(save.getUserSpecification().getDepartment().getParentId())
                        .build())
                .build();
    }
}
