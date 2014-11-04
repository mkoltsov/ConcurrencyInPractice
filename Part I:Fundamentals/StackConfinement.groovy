def range = 1..100
range.each { _ ->
    def gaussian = new Random(_).nextGaussian()
    new Thread(new Runnable() {
        @Override
        void run() {
            println(gaussian)
        }
    }).start()
}