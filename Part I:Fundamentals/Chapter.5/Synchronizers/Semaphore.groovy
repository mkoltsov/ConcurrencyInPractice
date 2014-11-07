def bounded  = new Runnable(){
//    int cnt = 1
final java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(1)
    @Override
    void run() {
        sem.acquire()
        println Thread.currentThread().getName() + " is doing some serious computations"
        sleep 500
        sem.release()
    }
}

new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()
new Thread(bounded).start()