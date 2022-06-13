package com.company.config.details;

import com.company.entity.ProfileEntity;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public CustomProfileDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        ProfileEntity entity = profileRepository
                .findByIdAndDeletedDateIsNull(id)
                .orElseThrow(() ->  {throw new ItemNotFoundException("Profile Not Found");});

        return new CustomProfileDetails(entity);
    }
}
