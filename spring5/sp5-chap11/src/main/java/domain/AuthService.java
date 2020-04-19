package domain;

import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AuthService {

    @Autowired
    private MemberDao memberDao;

    public AuthInfo authenticate(String email, String password){
        Member member = memberDao.selectByEmail(email);
        if(Objects.isNull(member)){
            throw new WrongIdPasswordException();
        }
        if(!member.matchPassword(password)){
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }
}
