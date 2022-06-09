package com.sparta.week5home.service;

import com.sparta.week5home.dto.requestDto.LoginRequestDto;
import com.sparta.week5home.dto.requestDto.SignupRequestDto;
import com.sparta.week5home.jwt.JwtTokenProvider;
import com.sparta.week5home.model.UserDomain.User;
import com.sparta.week5home.model.UserRoleEnum;
import com.sparta.week5home.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원 가입
    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        UserRoleEnum role = requestDto.getRole();
        String pattern = "^[a-zA-Z0-9]*$";

        // ID 중복 확인
        Optional<User> usedId = userRepository.findByUsername(username);
        if(usedId.isPresent()){    // return value != null;
            return "중복된 닉네임입니다.";
        }

        // ID 유효성 검사 : 3자리 이상 확인
        if(username.length()<3){
            return "아이디는 최소 3자리 이상입니다";
        } else if(!Pattern.matches(pattern, username)) {
            return "아이디는 영문 대소문자, 숫자만 가능합니다";
        } else if(password.length()<4){
            return "비밀번호는 최소 4자리 이상입니다.";
        } else if(password.contains(username)){
            return "비밀번호에는 ID를 포함 할 수 없습니다.";
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(password);  // 비밀번호 암호화

        // DB에 저장
        userRepository.save(new User(username, password, role));

        return "회원가입 성공";
    }

    // 로그인
    public String login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElse(null);
        if(user != null){
            if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){ return "비밀번호를 확인하세요"; }
        } else {return "없는 아이디 입니다.";}

        // jwt 토큰 생성
        return jwtTokenProvider.createToken(requestDto.getUsername());
    }
}
