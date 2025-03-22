package name.seguri.java.tutorials.chain;

abstract class Filter {
  protected Filter next;

  /** Links all filters in the order they are passed and returns the first one. */
  static Filter link(Filter first, Filter... remainingFilters) {
    var head = first;

    for (Filter nextFilter : remainingFilters) {
      head.next = nextFilter;
      head = nextFilter;
    }

    return first;
  }

  boolean hasNext() {
    return next != null;
  }

  abstract void handle(Request request, Response response);
}
