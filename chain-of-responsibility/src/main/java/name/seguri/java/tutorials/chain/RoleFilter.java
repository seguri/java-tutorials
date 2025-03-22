package name.seguri.java.tutorials.chain;

class RoleFilter extends Filter {

  @Override
  void handle(Request request, Response response) {
    if (!request.data.contains("admin")) {
      response.headers = "Content-Type:application/problem+json";
      response.data = "{\"status\": 403, \"title\": \"Forbidden\"}";
    } else if (hasNext()) {
      next.handle(request, response);
    }
  }
}
