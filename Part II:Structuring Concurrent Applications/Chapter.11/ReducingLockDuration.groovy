import java.util.concurrent.Executors

class BetterAttributeClass {
    private final Map<String, String> map = [travis: "kalan", steve: "jobs", jeff: "bezos"]

    public boolean doesItMatch(String name) {
        def surname;
        synchronized (this) {
            surname = map[name]
        }

        if (surname == null) {
            return false
        } else return true
    }
}

class Task3 implements Runnable {
    def final String name;
    def mapa = new BetterAttributeClass()

    Task3(name) {
        this.name = name
    }

    @Override
    void run() {
        println "found $name" + mapa.doesItMatch(name)
    }
}


Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    execute(new Task3("travis"))
    execute(new Task3("fdfdf"))
    execute(new Task3("jeff"))
}