package com.novin.invoicemanagementsystem.config;

import com.novin.invoicemanagementsystem.entity.Role;
import com.novin.invoicemanagementsystem.repository.RoleRepository;
import com.novin.invoicemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoles();

        alreadySetup = true;
    }

    @Transactional
    protected void createRoles() {
        createRoleIfNotExists("ADMINISTRATOR",
                "Can view the administration page and all the other pages");
        createRoleIfNotExists("ACCOUNTANT",
                "Can create invoices, view the list of invoices and the details of individual invoices");
        createRoleIfNotExists("USER",
                "Can view the list of invoices and the details of individual invoices");
    }

    @Transactional
    protected void createRoleIfNotExists(String name, String description) {
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
