package org.example;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.GeneralSecurityException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public final class Ciphers {
  private static final String ALGORITHM = "AES";
  private static final String CIPHER_MODE = "AES/ECB/PKCS5Padding";
  private static final String PASSPHRASE = "1234567890abcdef";

  public static byte[] encrypt(byte[] plaintext) throws GeneralSecurityException {
    var cipher = Cipher.getInstance(CIPHER_MODE);
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec());
    return cipher.doFinal(plaintext);
  }

  public static byte[] encrypt(String plaintext) throws GeneralSecurityException {
    return encrypt(plaintext.getBytes(UTF_8));
  }

  public static String encryptToBase64(String plaintext) throws GeneralSecurityException {
    var bytes = encrypt(plaintext);
    return Base64.getEncoder().encodeToString(bytes);
  }

  public static String encryptToHex(String plaintext) throws GeneralSecurityException {
    var bytes = encrypt(plaintext);
    var hex = Hex.encodeHexString(bytes, true);
    return StringUtils.leftPad(hex, 32, '0');
  }

  public static byte[] decrypt(byte[] encrypted) throws GeneralSecurityException {
    var cipher = Cipher.getInstance(CIPHER_MODE);
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec());
    return cipher.doFinal(encrypted);
  }

  public static String decryptToString(byte[] encrypted) throws GeneralSecurityException {
    return new String(decrypt(encrypted));
  }

  public static String decryptFromHexToString(String hex)
      throws GeneralSecurityException, DecoderException {
    // This doesn't always work
    // var bytes = new BigInteger(hex, 16).toByteArray();
    var bytes = Hex.decodeHex(hex);
    return decryptToString(bytes);
  }

  public static String decryptFromBase64ToString(String base64) throws GeneralSecurityException {
    var bytes = Base64.getDecoder().decode(base64.getBytes(UTF_8));
    return decryptToString(bytes);
  }

  private static SecretKeySpec secretKeySpec() {
    return new SecretKeySpec(PASSPHRASE.getBytes(UTF_8), ALGORITHM);
  }
}
