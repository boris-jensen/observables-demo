package my.group;

import java.util.List;

public class BumpRepo implements IBumpRepo {

    public void addBumps(List<String> bumps) {
        System.out.println(String.join(", ", bumps));
    }
}

