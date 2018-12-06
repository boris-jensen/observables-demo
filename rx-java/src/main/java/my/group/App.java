package my.group;

import static spark.Spark.*;
import my.group.AppService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class App 
{
    public static void main( String[] args) {
        AppService service = new AppService(Schedulers.io());
        IBumpRepo repo = new BumpRepo();
        service.getBumpBatches().subscribe(bs -> repo.addBumps(bs));
        post("/bump", (req, res) -> service.receiveBump(req.queryParams("bumpNo")));
    }
}
