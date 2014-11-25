import LiveLockExample.Spoon
import groovy.transform.Canonical

import java.util.concurrent.Executors

class LiveLockExample {
    static class Spoon {
        private Diner owner

        Spoon(diner) {
            this.owner = diner
        }

        def getOwner() {
            owner
        }

        public synchronized void setOwner(diner) {
            this.owner = diner
        }

        public synchronized void use() {
            println "has eaten $owner.name"
        }
    }

    @Canonical
    static class Diner {
        def String name
        def boolean isHungry

        def void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                //don't have a spoon, so wait patiently
                if (spoon.owner != this) {
                    sleep 1
                    continue
                }
                //if spouse is hungry insist upon passing the spoon
                if (spouse.isHungry) {
                    println "eat first dear $name  = $spouse.name"
                    spoon.setOwner(spouse)
                }

                //spouse wasn't hungry, so finally eat
                spoon.use()
                isHungry = false
                println "i'm stuffed darling $name $spouse.name"
                spoon.setOwner(spouse)
            }
        }
    }
}

def husband = new LiveLockExample.Diner("Chef", true)
def wife = new LiveLockExample.Diner("Pupa", true)

final Spoon spoon = new Spoon(husband)

Executors.newCachedThreadPool().with {
    execute(new Runnable() {
        @Override
        void run() {
            husband.eatWith(spoon, wife)
            wife.eatWith(spoon, husband)
        }
    })
}

//new Thread((new Runnable() {
//    @Override
//    void run() {
//        husband.eatWith(spoon, wife)
//
//    }
//})).start()
//
//new Thread((new Runnable() {
//    @Override
//    void run() {
//        wife.eatWith(spoon, husband)
//
//    }
//})).start()