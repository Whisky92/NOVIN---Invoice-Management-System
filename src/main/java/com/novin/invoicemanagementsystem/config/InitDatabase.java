package com.novin.invoicemanagementsystem.config;

import com.novin.invoicemanagementsystem.entity.Role;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.repository.RoleRepository;
import com.novin.invoicemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoles();
        createUsers();

        alreadySetup = true;
    }

    @Transactional
    void createRoles() {
        createInitialRole("ADMINISTRATOR",
                "Can view the administration page and all the other pages");
        createInitialRole("ACCOUNTANT",
                "Can create invoices, view the list of invoices and the details of individual invoices");
        createInitialRole("USER",
                "Can view the list of invoices and the details of individual invoices");
    }

    @Transactional
    void createUsers() {
        createInitialAdministrator();
        createInitialAccountant();
        createInitialUser();
    }

    @Transactional
    void createInitialAdministrator() {
        if (userRepository.findByUserName("INIT_ADMINISTRATOR").isEmpty()) {
            User administrator = User.builder()
                    .firstName("Initial")
                    .lastName("Administrator")
                    .userName("INIT_ADMINISTRATOR")
                    .password(passwordEncoder.encode("administrator"))
                    .roles(List.of(roleRepository.findByName("ADMINISTRATOR")))
                    .build();
            userRepository.save(administrator);
        }
    }

    @Transactional
    void createInitialAccountant() {
        if (userRepository.findByUserName("INIT_ACCOUNTANT").isEmpty()) {
            User accountant = User.builder()
                    .firstName("Initial")
                    .lastName("Accountant")
                    .userName("INIT_ACCOUNTANT")
                    .password(passwordEncoder.encode("accountant"))
                    .roles(List.of(roleRepository.findByName("ACCOUNTANT")))
                    .build();
            userRepository.save(accountant);
        }
    }

    @Transactional
    void createInitialUser() {
        if (userRepository.findByUserName("INIT_USER").isEmpty()) {
            User user = User.builder()
                    .firstName("Initial")
                    .lastName("User")
                    .userName("INIT_USER")
                    .password(passwordEncoder.encode("user"))
                    .roles(List.of(roleRepository.findByName("USER")))
                    .build();
            userRepository.save(user);
        }
    }

    @Transactional
    void createInitialRole(String name, String description) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.builder()
                    .name(name)
                    .description(description)
                    .build();
            roleRepository.save(role);
        }
    }
}
