package com.example.webClient.service.imp;

import com.example.webClient.dto.CommitDto;
import com.example.webClient.dto.RegisterDto;
import com.example.webClient.model.RegisterCustomer;
import com.example.webClient.repository.RegisterRepository;
import com.example.webClient.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RegisterServiceImp implements RegisterService {

    final RegisterRepository registerRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public RegisterServiceImp(RegisterRepository registerRepository, RedisTemplate<String, Object> redisTemplate) {
        this.registerRepository = registerRepository;
        this.redisTemplate = redisTemplate;
    }

    private String generateSessionCode() {
        int min = -9999999;
        int max = -1000000;
        // Generate a random number between min and max
        int sessionCode = (int) ((Math.random() * (max - min)) + min);

        return String.valueOf(sessionCode);
    }

    @Override
    public String saveToRedis(RegisterDto registerDto) {
        // Generate a unique session code
        String sessionCode = this.generateSessionCode();
        // Set expiration time in seconds
        long expirationTime = 1;

        // Save the RegisterDto object with a TTL
        redisTemplate.opsForValue().set(sessionCode, registerDto, expirationTime, TimeUnit.MINUTES);

        log.info("Data saved to Redis with session code: {} and expires in {} seconds", sessionCode, expirationTime);
        return sessionCode;
    }
    
    @Override
    public String commitData(CommitDto commitDto) {
        // Retrieve data from Redis
        String redisId = commitDto.getSessionId();
        RegisterDto registerDto = (RegisterDto) redisTemplate.opsForValue().get(redisId);

        if (registerDto == null) {
            throw new RuntimeException("Data not found in Redis for ID: " + redisId);
        }

        // Map RegisterDto to RegisterCustomer entity
        RegisterCustomer registerCustomer = new RegisterCustomer();
        registerCustomer.setName(registerDto.getUsername());
        registerCustomer.setAccount(registerDto.getRetailerAccount());
        registerCustomer.setPhoneNumber(registerDto.getPhoneNumber());
        registerCustomer.setOutletId(registerDto.getOutletId());

        // Save to database
        RegisterCustomer savedCustomer = registerRepository.save(registerCustomer);

        // Return confirmation or the saved entity's ID
        return "Data successfully committed with ID: " + savedCustomer.getId();
    }

}
