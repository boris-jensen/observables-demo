package my.group;

import java.util.List;
import io.reactivex.*;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class AppService {

    private Subject<String> bumps;
    private Observable<List<String>> bumpBatches;

    AppService() {
        this.bumps = PublishSubject.create();
        this.bumpBatches = bumps.buffer(3);
    }

    public boolean receiveBump(String bump) {
        this.bumps.onNext(bump);
        return true;
    }

    public Observable<List<String>> getBumpBatches() {
        return this.bumpBatches;
    }
}
