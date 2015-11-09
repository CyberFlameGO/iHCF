package com.doctordark.hcf.faction.event;

import com.doctordark.hcf.faction.event.cause.FactionLeaveCause;
import com.doctordark.hcf.faction.type.PlayerFaction;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Faction event called when a user has left their {@link com.doctordark.hcf.faction.type.Faction}.
 */
public class PlayerLeftFactionEvent extends FactionEvent {

    private static final HandlerList handlers = new HandlerList();

    private Optional<Player> player;

    @Getter
    private final UUID uniqueID;

    @Getter
    private final FactionLeaveCause cause;

    @Getter
    private final CommandSender sender;

    @Getter
    private final boolean isKick;

    @Getter
    private final boolean force;

    public PlayerLeftFactionEvent(CommandSender sender, @Nullable Player player, UUID playerUUID, PlayerFaction playerFaction, FactionLeaveCause cause, boolean isKick, boolean force) {
        super(playerFaction);

        Preconditions.checkNotNull(sender, "Sender cannot be null");
        Preconditions.checkNotNull(playerUUID, "Player UUID cannot be null");
        Preconditions.checkNotNull(playerFaction, "Player faction cannot be null");
        Preconditions.checkNotNull(cause, "Cause cannot be null");

        this.sender = sender;
        if (player != null) {
            this.player = Optional.of(player);
        }

        this.uniqueID = playerUUID;
        this.cause = cause;
        this.isKick = isKick;
        this.force = force;
    }

    public Optional<Player> getPlayer() {
        if (this.player == null) {
            this.player = Optional.fromNullable(Bukkit.getPlayer(uniqueID));
        }

        return player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public PlayerFaction getFaction() {
        return (PlayerFaction) super.getFaction();
    }
}

