package the.aes;

import javax.crypto.SecretKey;

public class User {

    private Page page;
    private SecretKey decryptedKey;
    private SecretKey encryptKey;

    public User(Page page, SecretKey decryptedKey, SecretKey encryptKey) {
        this.page = page;
        this.decryptedKey = decryptedKey;
        this.encryptKey = encryptKey;
    }

    public SecretKey getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(SecretKey encryptKey) {
        this.encryptKey = encryptKey;
    }

    public SecretKey getDecryptedKey() {
        return decryptedKey;
    }

    public void setDecryptedKey(SecretKey decryptedKey) {
        this.decryptedKey = decryptedKey;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
