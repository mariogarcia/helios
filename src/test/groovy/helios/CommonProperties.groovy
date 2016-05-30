package helios

class CommonProperties {

    static void classHasNoPublicConstructor(Class<?> clazz) {
        assert clazz.constructors.size() == 0
        assert clazz.newInstance()
    }
}
