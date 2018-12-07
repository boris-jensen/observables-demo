package my.group;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.*;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import io.reactivex.Scheduler;

public class AppService {

    private Subject<String> bumps;
    private Observable<List<String>> bumpBatches;

    AppService(IBumpRepo repo, Scheduler scheduler) {
        this.bumps = PublishSubject.create();
        this.bumpBatches = bumps.buffer(1, 1, TimeUnit.SECONDS, scheduler);
        this.getBumpBatches().subscribe(bs -> repo.addBumps(bs));
    }

    public boolean receiveBump(String bump) {
        this.bumps.onNext(bump);
        return true;
    }

    public Observable<List<String>> getBumpBatches() {
        return this.bumpBatches;
    }
}
