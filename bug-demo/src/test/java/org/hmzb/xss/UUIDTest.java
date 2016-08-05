package org.hmzb.xss;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by ptzhuf on 2016/8/3.
 */
public class UUIDTest {
    @Test
    public final void testUUIRFromName() {
        String name = "3a3-1-2-3-4";
        System.out.println(UUID.fromString(name));
    }
}
