# spring-conditionals

How to deal with `Bean`s depending on an external condition (env variable, etc.)

## `@Value`

Allows to write logic directly tight to a variable.

I find this useful when I have dependencies on external systems and I just want to insert a conditional into my business logic.

## `@ConditionalOnMissingBean`

### NoOp alternative

You have a bean that depends on an external connection, a particular infrastructure, a heavy system like Kafka, etc. and you want to write an alternative bean that just satisfies the dependency on this bean but does nothing.
The problem with this approach is that you have to provide fake versions of those infrastructure elements, making it more complicated than the `@Value` case, e.g. a fake `KafkaTemplate`.

If you can modify the original bean, you can make it implement a new interface and replace the dependency on the implementation with it.
Then you're able to provide a new implementation that does not require those dependencies (it just needs to implement the agreed behavior, and that can be empty).

