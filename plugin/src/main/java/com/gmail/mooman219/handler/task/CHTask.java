package com.gmail.mooman219.handler.task;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.gmail.mooman219.core.Loader;
import com.gmail.mooman219.frame.EventHelper;
import com.gmail.mooman219.handler.task.event.TickSecondAsyncEvent;
import com.gmail.mooman219.handler.task.event.TickSecondSyncEvent;
import com.gmail.mooman219.handler.task.store.ConfigTask;
import com.gmail.mooman219.layout.CowHandler;
import com.gmail.mooman219.layout.HandlerType;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CHTask extends CowHandler {
    private static final HandlerType type = HandlerType.TASK;
    public final Loader plugin;
    private static Manager manager;


    private ScheduledExecutorService asyncPool;
    public ConfigTask configTask;

    public CHTask(Loader plugin) {
        this.plugin = plugin;
    }

    @Override
    public HandlerType getType() {
        return type;
    }

    public static String getName() {
        return type.getName();
    }

    public static String getCast() {
        return type.getCast();
    }

    public static String getDirectory() {
        return type.getDirectory();
    }

    public Loader getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        configTask = new ConfigTask();
        manager = new Manager();
        Loader.info(getCast() + "Starting plugin threads");
        asyncPool = Executors.newScheduledThreadPool(ConfigTask.getData().pluginThreads, new ThreadFactoryBuilder().setNameFormat("Cow Plugin Thread - $d").build());
        Loader.info(getCast() + "Starting second clocks");

        manager.runPlugin(new Runnable() {
            @Override
            public void run() {
                EventHelper.callEvent(new TickSecondAsyncEvent());
            }
        }, 1000, 1000);

        manager.runBukkit(new Runnable() {
            @Override
            public void run() {
                EventHelper.callEvent(new TickSecondSyncEvent());
            }
        }, 20, 20);
    }

    public static Manager getManager() {
        return manager;
    }

    @Override
    public void onDisable() {
        Loader.info(getCast() + "Stopping all threads");
        asyncPool.shutdown();
        try {
            asyncPool.awaitTermination(ConfigTask.getData().timeoutDelay, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Manager {
        public boolean isInPool() {
            return Thread.currentThread().getName().startsWith("Glare Plugin Thread");
        }

        public BukkitTask runBukkit(Runnable runnable) {
            return Bukkit.getScheduler().runTask(getPlugin(), runnable);
        }

        public BukkitTask runBukkit(Runnable runnable, long delay) {
            return Bukkit.getScheduler().runTaskLater(getPlugin(), runnable, delay);
        }

        public BukkitTask runBukkit(Runnable runnable, long delay, long period) {
            return Bukkit.getScheduler().runTaskTimer(getPlugin(), runnable, delay, period);
        }

        public <T> Future<T> runPlugin(Callable<T> callable) {
            return asyncPool.submit(callable);
        }

        public Future<?> runPlugin(Runnable runnable) {
            return asyncPool.submit(runnable);
        }

        public Future<?> runPlugin(Runnable runnable, long delay) {
            return asyncPool.schedule(runnable, delay, TimeUnit.MILLISECONDS);
        }

        public Future<?> runPlugin(Runnable runnable, long delay, long period) {
            return asyncPool.scheduleAtFixedRate(runnable, delay, period, TimeUnit.MILLISECONDS);
        }
    }
}
