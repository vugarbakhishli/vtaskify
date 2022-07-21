package az.bakhishli.user.management.service.impl;

import az.bakhishli.common.dto.organization.RegisterReqDto;
import az.bakhishli.common.dto.user.UserDto;
import az.bakhishli.user.management.client.MsOrganizationUserClient;
import az.bakhishli.user.management.domain.Authority;
import az.bakhishli.user.management.domain.User;
import az.bakhishli.user.management.domain.VerificationToken;
import az.bakhishli.user.management.exceptions.EmailAlreadyUsedException;
import az.bakhishli.user.management.exceptions.TokenNotFoundException;
import az.bakhishli.user.management.repository.AuthorityRepository;
import az.bakhishli.user.management.repository.UserRepository;
import az.bakhishli.user.management.repository.VerificationTokenRepository;
import az.bakhishli.user.management.service.UserService;
import az.bakhishli.user.management.service.dto.auth.ActivateAccountDto;
import az.bakhishli.user.management.service.dto.auth.SignUpForOrganizationDto;
import az.bakhishli.user.management.service.dto.auth.SignUpForOrganizationUserDto;
import az.bakhishli.common.dto.user.UserIdContainerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static az.bakhishli.common.security.UserRole.ROLE_ADMIN;
import static az.bakhishli.common.security.UserRole.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ModelMapper mapper;
    private final AuthorityRepository authorityRepository;
    private final MsOrganizationUserClient msOrganizationUserClient;

    @Override
    @Transactional
    public void signUpForOrganization(SignUpForOrganizationDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getEmail());
                });
        User forOrganization = createUserEntityForOrganization(dto);

        var saved = userRepository.save(forOrganization);

        msOrganizationUserClient.register(RegisterReqDto
                .builder()
                        .organizationName(dto.getOrganizationName())
                        .address(dto.getAddress())
                        .phoneNumber(dto.getPhoneNumber())
                        .userId(saved.getId())
                        .build());
    }

    @Override
    public void signUpForUser(SignUpForOrganizationUserDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(dto.getEmail());
                });
        User forOrganizationUser = createUserEntityForOrganizationUser(dto);
        userRepository.save(forOrganizationUser);
    }

    @Override
    public void activateAccount(ActivateAccountDto dto) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(dto.getToken())
                .orElseThrow(TokenNotFoundException::new);
        User user = verificationToken.getUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsersById(UserIdContainerDto dto) {
        List<User> userList = userRepository.findAllById(dto.getId());
        return userList
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }


    private User createUserEntityForOrganization(SignUpForOrganizationDto dto) {
        User user = mapper.map(dto, User.class);
        Authority authority = Authority.builder().authority(ROLE_ADMIN.toString()).build();
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

    private User createUserEntityForOrganizationUser(SignUpForOrganizationUserDto dto) {
        User user = mapper.map(dto, User.class);
        Authority authority = Authority.builder().authority(ROLE_USER.toString()).build();
        authorityRepository.save(authority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        user.setAuthorities(userAuthority);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

}
