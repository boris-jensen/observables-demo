package my.group;

import static spark.Spark.*;
import my.group.AppService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class App 
{
    public static void main( String[] args) {
        IBumpRepo repo = new BumpRepo();
        AppService service = new AppService(repo, Schedulers.io());
        post("/bump", (req, res) -> service.receiveBump(req.queryParams("bumpNo")));
    }
}
