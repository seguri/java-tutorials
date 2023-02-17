package org.example;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class CiphersTest {

  @Test
  void encryptionPerformance() throws GeneralSecurityException {
    var start = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      Ciphers.encryptToBase64("a");
    }

    var stop = System.currentTimeMillis();
    var elapsed = stop - start;
    System.out.println(elapsed); // 94
  }

  @Test
  void encryptionPerformance2() throws GeneralSecurityException {
    var start = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      var uuid = UUID.randomUUID().toString().substring(0, 15);
      Ciphers.encryptToBase64(uuid);
    }

    var stop = System.currentTimeMillis();
    var elapsed = stop - start;
    System.out.println(elapsed); // 104
  }

  @Test
  void decryptionPerformance() throws GeneralSecurityException {
    var encryptedStrings = IntStream.range(0, 1000)
        .mapToObj(
            i -> {
              try {
                return Ciphers.encryptToBase64("" + i);
              } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
              }
            })
        .collect(Collectors.toList());

    var start = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      Ciphers.decryptFromBase64ToString(encryptedStrings.get(i));
    }

    var stop = System.currentTimeMillis();
    var elapsed = stop - start;
    System.out.println(elapsed); // 25
  }
}
