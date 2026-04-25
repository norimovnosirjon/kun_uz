package com.mbs.api.service;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.LoginRequestDTO;
import com.mbs.api.dto.request.RegistrationDTO;
import com.mbs.api.dto.request.SmsApproveDTO;
import com.mbs.api.dto.response.LoginResponseDTO;
import com.mbs.api.entity.ProfileEntity;
import com.mbs.api.entity.SmsEntity;
import com.mbs.api.enums.ProfileRole;
import com.mbs.api.enums.ProfileStatus;
import com.mbs.api.exception.AppBadRequestException;
import com.mbs.api.exception.ItemAlreadyExistsException;
import com.mbs.api.exception.ItemNotFoundException;
import com.mbs.api.repository.ProfileRepository;
import com.mbs.api.repository.SmsRepository;
import com.mbs.api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * @author 'Bilol Tuxtamurodov' on 07.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSendingService emailSendingService;
    private final Random random = new Random();
    private final SmsRepository smsRepository;

    public ApiResponse<String> registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrue(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.ACTIVE)) {
                throw new ItemAlreadyExistsException("Bunday foydalanuvchi mavjud");
            } else {
                throw new AppBadRequestException("Siz ro'yhatdan o'tgansiz iltimos email ni tasdiqlang");
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        profileRepository.save(entity);
        String s = emailSendingService.sendingSimpleMessage(dto.getEmail(), random.nextInt(10000, 100000));
        return ApiResponse.success(s);

    }

    public String approveSmsCode(SmsApproveDTO dto) {
        Optional<SmsEntity> optional = smsRepository.findTop1ByEmailAndUsedIsFalseOrderByCreatedDateDesc(dto.getEmail());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Sizga xabar yuborilmagan qayta sms jo'natishni amalga oshiring");
        }

        SmsEntity entity = optional.get();
        if (entity.getCode().equals(dto.getCode())) {
            profileRepository.updateStatus(dto.getEmail());
            smsRepository.updateUsedById(entity.getId());
            return "Muvaffaqiyatli ro'yhatdan o'tdingiz";
        } else {
            throw new AppBadRequestException("Code mos emas qayta tekshirib urunib ko'ring");
        }
    }

    public ApiResponse<LoginResponseDTO> login(LoginRequestDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrue(dto.getEmail());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Bunday foydalanuvchi mavjud emas");
        }
        ProfileEntity entity = optional.get();
        if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            log.warn("Email tasdiqlanmagan : {}", entity.getEmail());
            throw new AppBadRequestException("Avval emailni tasdiqlang");
        }
        if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
            log.warn("Password xato; email : {}, password : {}", dto.getEmail(), dto.getPassword());
            throw new AppBadRequestException("Email yoki password noto'g'ri");
        }
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setRole(entity.getRole());
        response.setAccessToken(JwtUtil.encode(entity.getEmail(), entity.getRole().name()));
        return ApiResponse.success(response);
    }
}
