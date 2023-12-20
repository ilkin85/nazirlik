//package com.mia.xrs;
//
//import com.mia.xrs.entity.Department;
//import com.mia.xrs.entity.Role;
//import com.mia.xrs.entity.User;
//import com.mia.xrs.entity.UserSpecification;
//import com.mia.xrs.repository.DepartmentRepository;
//import com.mia.xrs.repository.RoleRepository;
//import com.mia.xrs.repository.UserRepository;
//import com.mia.xrs.repository.UserSpecificationRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class UserCommandLineRunner implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserSpecificationRepository userSpecificationRepository;
//    private final DepartmentRepository deparmentRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Role roleAdmin = roleRepository.save(Role.builder().authority("ROLE_ADMIN").build());
////        Role roleEditor = roleRepository.save(Role.builder().authority("ROLE_EDITOR").build());
////
//
//        UserSpecification userSpecification = new UserSpecification();
//        userSpecification.setFirstName("zamir");
//        userSpecification.setLastName("safa");
//        userSpecification.setFatherName("azad");
//        userSpecification.setPoliceCard("1111");
//        userSpecification.setRank("kur");
//
//        Department department = new Department();
//        department.setName("Biki");
//        department.setParentId(1);
//        deparmentRepository.save(department);
//
//        userSpecification.setDepartment(department);
//        userSpecificationRepository.save(userSpecification);
//
//        userRepository.save(User.builder()
//                .username("teymur")
//                .password(passwordEncoder.encode("zamir.2002"))
//                .isEnabled(true)
//                .authorities(List.of(roleAdmin))
//                .userSpecification(userSpecification)
//                .build());
////
////        userRepository.save(User.builder()
////                .username("farid")
////                .password(passwordEncoder.encode("farid.2002"))
////                .isEnabled(true)
////                .roles(List.of(roleAdmin))
////                .build());
////
////        userRepository.save(User.builder()
////                .username("teymur")
////                .password(passwordEncoder.encode("teymur.2003"))
////                .isEnabled(true)
////                .roles(List.of(roleEditor))
////                .build());
//
//    }
//}
