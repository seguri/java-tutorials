# springbank

## Troubleshooting

### Lombok code not recognized by Intellij

Code works perfectly fine with Maven.
Intellij instead fails to recognize all lombok-related code.
All Intellij settings and plugins seem to be correctly configured.

One thing I've noticed: the `annotationProcessorPaths` of the `pom.xml` is synced with ` Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processor`, in particular with `Annotation profile for Spring Bank -> springbank -> Processor path`.
There you can notice that if you don't specify a lombok version in the `<path>` section, the processor points to an invalid path: `~/.m2/repository/org/projectlombok/lombok/unknown/lombok-unknown.jar`.
That part of the pom can't use the `lombok.version` coming from `spring-boot-dependencies`, so I had to manually hardcode it. 
