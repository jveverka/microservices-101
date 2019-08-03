package itx.examples.mlapp.apis;

import io.grpc.stub.StreamObserver;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BlockingObserver<T> implements StreamObserver<T> {

    private final CountDownLatch cl;
    private T value;
    private Throwable t;

    public BlockingObserver() {
        cl = new CountDownLatch(1);
    }

    @Override
    public void onNext(T value) {
        this.value = value;
        cl.countDown();
    }

    @Override
    public void onError(Throwable t) {
        this.t = t;
        cl.countDown();
    }

    @Override
    public void onCompleted() {
        cl.getCount();
    }

    public Optional<T> awaitForValue(long time, TimeUnit unit) throws Exception {
        if (t != null) {
            throw new Exception(t);
        }
        boolean result = cl.await(time, unit);
        if (!result) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(value);
        }
    }

}
