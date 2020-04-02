package com.issuefinder.crawling.mapper;

import com.issuefinder.crawling.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    Boolean existsByEmail(String username);

    User findByUsername(String username);
    User findByEmail(String email);

    void save(User user);

    void deleteByUsername(String username);

    /*
회원가입 api
>> 파라미터 변경(성별/이름/ 생년월일 추가, 이메일 제거- 기획서내..  ---> DB필드 수정필요)
>> 요청시 ROLE설정 <- 확인 필요
아이디 중복확인 api
비밀번호 찾기 api
아이디 찾기 api
휴대폰 인증번호 api
문의하기 api (DB생성필요)
     */


}
