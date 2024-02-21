package xhu.spike.fusion.encrypt.string;

public interface Algorithm {
    String encrypt(String orig);
    String decrypt(String orig);
    String recode(String orig);
    String genMethod();
}
