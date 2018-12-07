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
        TestBumpRepo repo = new TestBumpRepo();
        TestScheduler scheduler = new TestScheduler();
        AppService service = new AppService(repo, scheduler);

        scheduler.scheduleDirect(() -> service.receiveBump("1"));
        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        List<List<String>> calls = repo.getCalls();
        Assert.assertEquals(calls.size(), 1);
        Assert.assertEquals(calls.get(0).size(), 1);

        String[] actualCall = calls.get(0).toArray(new String[1]);
        String[] expectedCall = new String[] {"1"};
        Assert.assertTrue(Arrays.equals(actualCall, expectedCall));
    }

    @Test
    public void testBufferDoesNotEmitBefore1second() {
        TestBumpRepo repo = new TestBumpRepo();
        TestScheduler scheduler = new TestScheduler();
        AppService service = new AppService(repo, scheduler);

        scheduler.scheduleDirect(() -> service.receiveBump("1"));
        scheduler.advanceTimeBy(999, TimeUnit.MILLISECONDS);

        Assert.assertEquals(repo.getCalls().size(), 0);
    }
}
