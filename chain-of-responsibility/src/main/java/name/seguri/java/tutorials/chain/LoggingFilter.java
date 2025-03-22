package name.seguri.java.tutorials.chain;

/**
 * The chain of responsibility pattern gives you the flexibility to decide if you want to manage a
 * response before or after all other filters have interacted with the request. Here we want to log
 * the response after all other filters have potentially modified it.
 */
class LoggingFilter extends Filter {

  @Override
  void handle(Request request, Response response) {
    if (hasNext()) {
      next.handle(request, response);
    }

    System.out.printf("%s -> %s%n", request, response);
  }
}
