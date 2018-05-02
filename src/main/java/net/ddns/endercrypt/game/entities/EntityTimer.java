package net.ddns.endercrypt.game.entities;

import java.io.Serializable;

public interface EntityTimer extends Serializable
{
	public void call() throws Exception;
}
