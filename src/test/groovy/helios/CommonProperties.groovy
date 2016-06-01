package helios

import groovy.transform.CompileStatic

@CompileStatic
class CommonProperties {

    static void classHasNoPublicConstructor(Class<?> clazz) {
        assert clazz.constructors.size() == 0
        assert clazz.newInstance()
    }
}
