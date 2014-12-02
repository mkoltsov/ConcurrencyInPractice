/**
 * Created by misha_ring on 12/3/14.
 */

class SafeStates {

    private final Map<String, String> map

    SafeStates() {
        map = ["chef": "pupa", "buba": "diop"]
    }

    def getName(name) {
        return map.get(name)
    }
}