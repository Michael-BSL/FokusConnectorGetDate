package com.bsl;


/**
 * Created by IntelliJ IDEA.
 * User: hakgu
 * Date: 5/6/11
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class JoltPoolStarter extends JoltPoolManager
{
    public JoltPoolStarter() {
        //
    }

    public final void startPool() throws Exception {
        start();
    }

    public static final void main(final String[] args) {
        try {
            new JoltPoolStarter().startPool();
        } catch (Throwable t) {
            System.err.println(
                    JoltPoolStarter.class.getName()
                            + ": Failed to initialise Jolt pool with "
                            + ((args.length == 0) ? "no argument" : ("one argument (" + args[0] + ")"))
            );
            t.printStackTrace();
        }
    }
}
