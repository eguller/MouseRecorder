package com.eguller.mouserecorder.player;

import com.eguller.mouserecorder.player.api.Player;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

import javax.swing.*;

/**
 * User: eguller
 * Date: 3/13/14
 * Time: 8:28 AM
 */
public class StopHotKeyListener implements HotKeyListener {
    public static final String STOP_HOTKEY = "ctrl F2";
    Player player = null;
    Provider provider = null;

    public StopHotKeyListener(Player player) {
        this.player = player;
    }

    @Override
    public void onHotKey(HotKey hotKey) {
        player.stop();
    }

    public void register() {
        provider = Provider.getCurrentProvider(true);
        provider.register(KeyStroke.getKeyStroke(STOP_HOTKEY), this);
    }

    public void deregister() {
        provider.reset();
        ;
        provider.stop();
    }
}
