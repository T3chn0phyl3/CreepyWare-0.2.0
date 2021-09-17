// 
// Decompiled by Procyon v0.5.36
// 

package dev.fxcte.creepyware.features.command.commands;

import java.util.Iterator;
import dev.fxcte.creepyware.CreepyWare;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import dev.fxcte.creepyware.features.command.Command;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("config", new String[] { "<save/load>" });
    }
    
    @Override
    public void execute(final String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage("You`ll find the config files in your gameProfile directory under creepyware/config");
            return;
        }
        if (commands.length == 2) {
            if ("list".equals(commands[0])) {
                String configs = "Configs: ";
                final File file = new File("creepyware/");
                final List<File> directories = Arrays.stream(file.listFiles()).filter(File::isDirectory).filter(f -> !f.getName().equals("util")).collect((Collector<? super File, ?, List<File>>)Collectors.toList());
                final StringBuilder builder = new StringBuilder(configs);
                for (final File file2 : directories) {
                    builder.append(file2.getName() + ", ");
                }
                configs = builder.toString();
                Command.sendMessage("§a" + configs);
            }
            else {
                Command.sendMessage("§cNot a valid command... Possible usage: <list>");
            }
        }
        if (commands.length >= 3) {
            final String s = commands[0];
            switch (s) {
                case "save": {
                    CreepyWare.configManager.saveConfig(commands[1]);
                    Command.sendMessage("§aConfig has been saved.");
                    break;
                }
                case "load": {
                    CreepyWare.moduleManager.onUnload();
                    CreepyWare.configManager.loadConfig(commands[1]);
                    CreepyWare.moduleManager.onLoad();
                    Command.sendMessage("§aConfig has been loaded.");
                    break;
                }
                default: {
                    Command.sendMessage("§cNot a valid command... Possible usage: <save/load>");
                    break;
                }
            }
        }
    }
}
