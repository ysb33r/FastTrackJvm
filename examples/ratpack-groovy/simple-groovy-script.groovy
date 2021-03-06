@Grapes([
    @Grab('io.ratpack:ratpack-groovy:1.2.0'),
    @Grab('org.slf4j:slf4j-simple:1.7.12')
])
import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
        get {
            render "Hello World!\n\n"
        }
        get(":name") {
            render "Hello $pathTokens.name!\n\n"
        }
    }
}
