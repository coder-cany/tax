package com.hs.user.cache;

import com.hs.user.po.User;

import java.util.concurrent.ConcurrentLinkedQueue;

public class UserToInsertDBQueue {
    public static final ConcurrentLinkedQueue<User> queue = new ConcurrentLinkedQueue<User>();

    public static void add(User o) {
        queue.add(o);
    }

    public static User consumeToInsert() {
        return queue.poll();
    }
}
