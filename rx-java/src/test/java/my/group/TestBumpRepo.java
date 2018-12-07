package my.group;

import java.util.List;
import java.util.ArrayList;

public class TestBumpRepo implements IBumpRepo {

    private List<List<String>> calls = new ArrayList<>();

    public void addBumps(List<String> bumps) {
        calls.add(bumps);
    }

    public List<List<String>> getCalls() {
        return this.calls;
    }
}
