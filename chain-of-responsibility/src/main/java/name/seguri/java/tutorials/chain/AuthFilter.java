package name.seguri.java.tutorials.chain;

class AuthFilter extends Filter {

  @Override
  void handle(Request request, Response response) {
    if (!request.data.contains("authenticated")) {
      response.headers = "Content-Type:application/problem+json";
      response.data = "{\"status\": 401, \"title\": \"Unauthorized\"}";
    } else if (hasNext()) {
      next.handle(request, response);
    }
  }
}
