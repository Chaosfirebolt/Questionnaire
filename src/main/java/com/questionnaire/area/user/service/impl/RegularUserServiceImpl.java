package com.questionnaire.area.user.service.impl;

import com.questionnaire.area.role.entity.Role;
import com.questionnaire.area.role.repository.RoleRepository;
import com.questionnaire.area.user.dto.bind.UserRegistrationBind;
import com.questionnaire.area.user.entity.RegularUser;
import com.questionnaire.area.user.repository.RegularUserRepository;
import com.questionnaire.area.user.service.interfaces.RegularUserService;
import com.questionnaire.util.constants.error.LoginError;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
@Service
public class RegularUserServiceImpl extends UserServiceImpl implements RegularUserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public RegularUserServiceImpl(RegularUserRepository regularUserRepository, RoleRepository roleRepository,
                                  BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        super(regularUserRepository, roleRepository);
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationBind userBind) {
        String encryptedPassword = this.bCryptPasswordEncoder.encode(userBind.getPassword());
        userBind.setPassword(encryptedPassword);
        RegularUser newRegularUser = this.modelMapper.map(userBind, RegularUser.class);
        this.setAccountData(newRegularUser);

        Role defaultRole = super.getDefaultUserRole();
        newRegularUser.addRole(defaultRole);

        RegularUserRepository regularUserRepository = (RegularUserRepository) super.getUserRepository();
        regularUserRepository.saveAndFlush(newRegularUser);
    }

    private void setAccountData(RegularUser newRegularUser) {
        final boolean IS_ACCOUNT_NON_EXPIRED = true;
        final boolean IS_ACCOUNT_NON_LOCKED = true;
        final boolean IS_CREDENTIALS_NON_EXPIRED = true;
        final boolean IS_ENABLED = true;
        newRegularUser.setAccountNonExpired(IS_ACCOUNT_NON_EXPIRED);
        newRegularUser.setAccountNonLocked(IS_ACCOUNT_NON_LOCKED);
        newRegularUser.setCredentialsNonExpired(IS_CREDENTIALS_NON_EXPIRED);
        newRegularUser.setEnabled(IS_ENABLED);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegularUserRepository regularUserRepository = (RegularUserRepository) super.getUserRepository();
        RegularUser regularUser = regularUserRepository.findOneByUsername(username);
        if (regularUser == null) {
            throw new UsernameNotFoundException(LoginError.INVALID_CREDENTIALS);
        }
        return regularUser;
    }
}