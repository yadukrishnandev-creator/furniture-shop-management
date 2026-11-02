package com.furnitureshop.user;

import com.furnitureshop.branch.Branch;
import com.furnitureshop.branch.BranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            BranchRepository branchRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username already exists"
            );
        }

        Branch branch = null;

        if (request.role() != Role.ADMIN) {

            if (request.branchId() == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Branch is required for Branch Manager and Staff"
                );
            }

            branch = branchRepository.findById(request.branchId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Branch not found"
                    ));
        }

        String passwordHash = passwordEncoder.encode(
                request.password()
        );

        User user = new User(
                request.username(),
                passwordHash,
                request.role(),
                branch
        );

        userRepository.save(user);
    }
}