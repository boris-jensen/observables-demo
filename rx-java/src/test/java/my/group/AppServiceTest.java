package my.group;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

/**
 * Unit test for AppService.
 */
public class AppServiceTest {

    @Test
    public void testBufferEmitsAfter1second() {
        TestScheduler scheduler = new TestScheduler();
        AppService service = new AppService(scheduler);
        Observable<List<String>> batches = service.getBumpBatches();
        List<List<String>> results = new ArrayList<List<String>>();
        batches.subscribe(buffer -> results.add(buffer));

        scheduler.scheduleDirect(() -> service.receiveBump("1"));
        scheduler.advanceTimeBy(1, TimeUnit.NANOSECONDS);
        scheduler.scheduleDirect(() -> service.receiveBump("2"));
        scheduler.advanceTimeBy(1, TimeUnit.NANOSECONDS);
        scheduler.scheduleDirect(() -> service.receiveBump("3"));
        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        Assert.assertEquals(results.size(), 1);
        String[] actual = results.get(0).toArray(new String[3]);
        String[] expected = new String[] {"1", "2", "3"};
        Assert.assertEquals(actual.length, 3);
        Assert.assertTrue(Arrays.equals(actual, expected));
    }

    @Test
    public void testBufferDoesNotEmitBefore1second() {
        TestScheduler scheduler = new TestScheduler();
        AppService service = new AppService(scheduler);
        Observable<List<String>> batches = service.getBumpBatches();
        List<List<String>> results = new ArrayList<List<String>>();
        batches.subscribe(buffer -> results.add(buffer));

        scheduler.scheduleDirect(() -> service.receiveBump("1"));
        scheduler.advanceTimeBy(1, TimeUnit.NANOSECONDS);
        scheduler.scheduleDirect(() -> service.receiveBump("2"));
        scheduler.advanceTimeBy(1, TimeUnit.NANOSECONDS);
        scheduler.scheduleDirect(() -> service.receiveBump("3"));
        scheduler.advanceTimeBy(997, TimeUnit.MILLISECONDS);

        Assert.assertEquals(results.size(), 0);
    }
}
