package org.example;

import java.security.GeneralSecurityException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;

/**
 * See <a href="https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html">Standard
 * Names</a>.
 */
public class App {
  private static final String RESOURCE = "/my.csv";

  public static void base64Solution(String resourceName) throws GeneralSecurityException {
    for (String line : ResourceUtils.readResourceAsStrings(resourceName)) {
      var encrypted = Ciphers.encryptToBase64(line);
      var decrypted = Ciphers.decryptFromBase64ToString(encrypted);
      validate(line, decrypted);
      System.out.printf("%s %s %s%n", line, encrypted, decrypted);
    }
  }

  /** This is ideal to create reversible UUIDs when input is <= 16 bytes. */
  public static void hexSolution(String resourceName) throws GeneralSecurityException, DecoderException {
    for (String line : ResourceUtils.readResourceAsStrings(resourceName)) {
      var encrypted = Ciphers.encryptToHex(line);
      validateHex(encrypted);
      var decrypted = Ciphers.decryptFromHexToString(encrypted);
      validate(line, decrypted);
      System.out.printf("%s %s %s%n", line, encrypted, decrypted);
    }
  }

  public static void main(String[] args) throws Exception {
    base64Solution(RESOURCE);
    hexSolution(RESOURCE);
  }

  private static void validateHex(String hex) {
    if (hex.length() != 32) {
      throw new RuntimeException("hex length is not 32 but " + hex.length());
    }
  }

  private static void validate(String plaintext, String decrypted) {
    if (!StringUtils.equals(plaintext, decrypted)) {
      throw new RuntimeException(decrypted + " is not equal to " + plaintext);
    }
  }
}
