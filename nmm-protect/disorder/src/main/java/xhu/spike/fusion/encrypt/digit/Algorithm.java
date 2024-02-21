package xhu.spike.fusion.encrypt.digit;

public interface Algorithm {
    int encrypt(int orig);
    int decrypt(int orig);
    String recode(int orig);
    String genMethod();
}
