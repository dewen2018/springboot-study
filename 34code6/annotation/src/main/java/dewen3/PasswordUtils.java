package dewen3;

public class PasswordUtils {
    @UseCases(id = "47",description = "PassWord must contain at least one numerric")
    public Boolean validatePassWord(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCases(id="48")
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }
}
