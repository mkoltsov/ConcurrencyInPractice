import groovy.transform.Immutable

import java.util.concurrent.Callable

@Immutable
class ProductInfo {
    String threadName
}

def curThr = { Thread.currentThread().getName() }
def future = new java.util.concurrent.FutureTask<ProductInfo>(new Callable<ProductInfo>() {
    @Override
    ProductInfo call() throws Exception {
        return new ProductInfo(threadName: curThr())
    }
})
new Thread(future).start()


println future.get().threadName
println curThr()
