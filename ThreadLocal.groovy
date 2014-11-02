java.lang.ThreadLocal<Double> valHolder = new java.lang.ThreadLocal()

def range = 1..100
range.each { _ ->
    def gaussian = new Random(_).nextGaussian()
    new Thread(new Runnable() {
        @Override
        void run() {
            valHolder.set(gaussian)
            println valHolder.get()
        }
    }).start()
}