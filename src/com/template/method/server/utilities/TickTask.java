package com.template.method.server.utilities;

import com.template.method.server.ServerOutput;
import com.template.method.server.command.Tick;
import java.util.List;

/**
 * Generates tick after waiting given time
 */
public class TickTask extends Thread {

    // list of server outputs objects
    protected List<ServerOutput> serverOutputs;
    //integer value to send the tick (milliseconds)
    protected long time;
    // synchronization object
    private final Object tickDummy;
    private boolean ticking = true;

    /**
     * @param serverOutputs  All threads server outputs
     * @param time           time until tick production
     * @param tickDummy     synchronization (mutex) object
     */
    public TickTask(List<ServerOutput> serverOutputs, long time, Object tickDummy) {
        this.serverOutputs = serverOutputs;
        this.time = time;
        this.tickDummy = tickDummy;
    }

    /**
     * Sleeps for given amount of time and sends Tick to clients
     */
    public void run() {
        while (ticking) {
            try {
                sleep(time);
                for (ServerOutput serverOutput : serverOutputs) {
                    serverOutput.addSendable(new Tick());

                    synchronized (tickDummy) {
                        tickDummy.notifyAll();
                    }
                }
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }


    public void accelerateTick(double factor) {
        time /= factor;
    }

    /**
     * Destroy thread timer task.
     */
    public void destroyTickTask() {
        this.ticking = false;
    }
}
