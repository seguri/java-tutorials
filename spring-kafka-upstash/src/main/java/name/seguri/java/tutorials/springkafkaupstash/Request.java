package name.seguri.java.tutorials.springkafkaupstash;

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
    String city) {}
