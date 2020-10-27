package co.inventorsoft.mailsecurity.services;

import co.inventorsoft.mailsecurity.forms.UserForm;
import co.inventorsoft.mailsecurity.models.Role;
import co.inventorsoft.mailsecurity.models.State;
import co.inventorsoft.mailsecurity.models.User;
import co.inventorsoft.mailsecurity.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .hashPassword(hashPassword)
                .login(userForm.getLogin())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        usersRepository.save(user);
    }
}
