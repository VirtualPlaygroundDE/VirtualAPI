package de.virtualplayground.plugin.command;

import de.virtualplayground.api.VirtualAPI;
import de.virtualplayground.api.player.VirtualPlayer;
import de.virtualplayground.lib.command.BaseCommand;
import de.virtualplayground.lib.lang.Lang;
import de.virtualplayground.plugin.VirtualPlugin;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.entity.Player;

public class CoinsCommand extends BaseCommand {

    public CoinsCommand(VirtualPlugin plugin) {

        super("coins");

        command.withAliases("balance", "money");

        command
                .withOptionalArguments(new PlayerArgument("target"))
                .executesPlayer((player, args) -> {

                    Player target = (Player) args.get("target");

                    if (target != null) {

                        int balance = VirtualAPI.getInstance().getPlayerManager().getPlayer(target).getCoins();
                        player.sendMessage(plugin.getLanguage().get("balanceOthers")
                                .replaceText(Lang.replace("%player%", target.getName()))
                                .replaceText(Lang.replace("%balance%", "" + balance))
                        );

                    } else {

                        int balance = VirtualAPI.getInstance().getPlayerManager().getPlayer(player).getCoins();
                        player.sendMessage(plugin.getLanguage().get("balance")
                                .replaceText(Lang.replace("%balance%", "" + balance))
                        );
                    }

                });

        command.withSubcommands(

                new CommandAPICommand("set")
                        .withPermission(getPermission() + ".set")
                        .withArguments(new PlayerArgument("player"))
                        .withArguments(new IntegerArgument("amount"))
                        .executes((sender, args) -> {

                            VirtualAPI api = VirtualAPI.getInstance();

                            Player player = (Player) args.get("player");
                            Integer amount = (Integer) args.get("amount");

                            if (player != null && amount != null) {

                                VirtualPlayer virtualPlayer = api.getPlayerManager().getPlayer(player);
                                int oldBalance = virtualPlayer.getCoins();
                                virtualPlayer.setCoins(amount);
                                int newBalance = virtualPlayer.getCoins();

                                sender.sendMessage(plugin.getLanguage().get("balanceChanged")
                                        .replaceText(Lang.replace("%player%", player.getName()))
                                        .replaceText(Lang.replace("%oldBalance%", "" + oldBalance))
                                        .replaceText(Lang.replace("%newBalance%", "" + newBalance))
                                );
                            }
                        }),

                new CommandAPICommand("add")
                        .withPermission(getPermission() + ".add")
                        .withArguments(new PlayerArgument("player"))
                        .withArguments(new IntegerArgument("amount"))
                        .executes((sender, args) -> {

                            VirtualAPI api = VirtualAPI.getInstance();

                            Player player = (Player) args.get("player");
                            Integer amount = (Integer) args.get("amount");

                            if (player != null && amount != null) {

                                VirtualPlayer virtualPlayer = api.getPlayerManager().getPlayer(player);
                                int oldBalance = virtualPlayer.getCoins();
                                virtualPlayer.addCoins(amount);
                                int newBalance = virtualPlayer.getCoins();

                                sender.sendMessage(plugin.getLanguage().get("balanceChanged")
                                        .replaceText(Lang.replace("%player%", player.getName()))
                                        .replaceText(Lang.replace("%oldBalance%", "" + oldBalance))
                                        .replaceText(Lang.replace("%newBalance%", "" + newBalance))
                                );

                            }
                        }),

                new CommandAPICommand("remove")
                        .withPermission(getPermission() + ".remove")
                        .withArguments(new PlayerArgument("player"))
                        .withArguments(new IntegerArgument("amount"))
                        .executes((sender, args) -> {

                            VirtualAPI api = VirtualAPI.getInstance();

                            Player player = (Player) args.get("player");
                            Integer amount = (Integer) args.get("amount");

                            if (player != null && amount != null) {

                                VirtualPlayer virtualPlayer = api.getPlayerManager().getPlayer(player);
                                int oldBalance = virtualPlayer.getCoins();
                                virtualPlayer.removeCoins(amount);
                                int newBalance = virtualPlayer.getCoins();

                                sender.sendMessage(plugin.getLanguage().get("balanceChanged")
                                        .replaceText(Lang.replace("%player%", player.getName()))
                                        .replaceText(Lang.replace("%oldBalance%", "" + oldBalance))
                                        .replaceText(Lang.replace("%newBalance%", "" + newBalance))
                                );
                            }
                        })
        );

    }
}
