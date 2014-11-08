import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Future

interface Computable<A,V>{
    V compute(A args);
}

 class Memoizer implements Computable<BigInteger,BigInteger>{
   private  final ConcurrentMap<BigInteger, Future<BigInteger>>

     @Override
     BigInteger compute(BigInteger args) {
         return null
     }
 }