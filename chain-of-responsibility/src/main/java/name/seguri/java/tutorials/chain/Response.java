package name.seguri.java.tutorials.chain;

import java.util.StringJoiner;

class Response {

  String headers = "Content-Type:application/json";
  String data = "{\"status\": 204, \"title\": \"No Content\"}";

  @Override
  public String toString() {
    return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
        .add("headers='" + headers + "'")
        .add("data='" + data + "'")
        .toString();
  }
}
