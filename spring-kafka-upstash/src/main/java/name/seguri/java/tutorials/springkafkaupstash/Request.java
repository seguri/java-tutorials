package name.seguri.java.tutorials.springkafkaupstash;

import java.io.Serializable;

public record Request(
    Long timestamp,
    String ray,
    String ip,
    String agent,
    String method,
    String url,
    String proto,
    String path,
    String country,
    String city)
    implements Serializable {

  public String paddedIp() {
    // 15 for IPv4, 39 for IPv6
    return String.format("%-39s", ip);
  }

  public String paddedCity() {
    return String.format("%-16s", city != null ? city : "");
  }
}
