package com.ndhuy.auth.user.domain.valueobject;

import org.springframework.security.crypto.bcrypt.BCrypt;

public record Password(String value) {
    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
    }


    public static Password of(String password){
        return new Password(password);
    }



    private String hashString(String value){
        return BCrypt.hashpw(value, BCrypt.gensalt());
    }
    public String hashPassword(String password){ 
        return hashString(password);
    }
    public void hashPassword(){
         BCrypt.hashpw(this.value, BCrypt.gensalt());
    }
    public boolean checkPassword(String password){
        return BCrypt.checkpw(password, this.value);
    }
    public boolean checkPassword(Password password){
        return BCrypt.checkpw(password.value(), this.value);
    }

}
