package name.seguri.java.tutorials.chain;

import java.util.StringJoiner;

class Request {
  String data = "";

  public Request(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Request.class.getSimpleName() + "[", "]")
        .add("data='" + data + "'")
        .toString();
  }
}
