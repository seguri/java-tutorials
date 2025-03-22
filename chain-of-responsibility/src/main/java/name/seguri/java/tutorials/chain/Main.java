package name.seguri.java.tutorials.chain;

public class Main {

  public static void main(String[] args) {
    Filter first;

    var anonymousRequest = new Request("anonymous");
    var response1 = new Response();
    first = Filter.link(new LoggingFilter(), new AuthFilter());
    first.handle(anonymousRequest, response1);

    var authenticatedRequest = new Request("authenticated");
    var response2 = new Response();
    first = Filter.link(new LoggingFilter(), new AuthFilter(), new RoleFilter());
    first.handle(authenticatedRequest, response2);

    var adminRequest = new Request("authenticated admin");
    var response3 = new Response();
    first = Filter.link(new LoggingFilter(), new AuthFilter(), new RoleFilter());
    first.handle(adminRequest, response3);
  }
}
