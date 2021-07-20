package Tim9.ImiPoslovi;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordCrypt {
    private static int difficulty=10;

    public static String hashPasword(String password){
        String salt= BCrypt.gensalt(difficulty);
        String passwordH=BCrypt.hashpw(password,salt);
        return passwordH;
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
