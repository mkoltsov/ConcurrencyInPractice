import groovy.transform.ToString

import java.util.concurrent.ConcurrentHashMap

@ToString(includeNames = true)
class Point {
    final int x
    final int y

    Point(x, y) {
        this.x = x
        this.y = y
    }
}

class VehicleTracker1 {
    private final ConcurrentHashMap<String, Point> locations
    private final Map<String, Point> unmodifiableMap

    VehicleTracker1(Map<String, Point> loc) {
        locations = new ConcurrentHashMap<String, Point>(loc)
        unmodifiableMap = Collections.unmodifiableMap(locations)
    }

    public getLocations() {
        //can return a new Map(static copy) instead of a live version of existing one
        //Collections.unmodifiableMap(new HashMap<String, Point>(locations))
        unmodifiableMap
    }

    public getLocation(id) {
        locations.get(id)
    }

    public void setLocation(String id, x, y) {
        locations.putIfAbsent(id, new Point(x, y))
    }
}

def ab = new Runnable() {
    private final mapa = ["Moscow": new Point(1, 2), "San Francisco": new Point(3, 4)]
    VehicleTracker1 vt = new VehicleTracker1(mapa)

    @Override
    void run() {
        synchronized (mapa) {
            vt.setLocation("city" + new Random().nextInt(100), new Random().nextInt(100), new Random().nextInt(100))
            println vt.getLocations()
        }
    }
}
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()