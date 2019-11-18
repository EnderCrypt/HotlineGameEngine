package endercrypt.hotline.engine.entities;

import java.io.Serializable;

public interface EntityTimer extends Serializable
{
	public void call() throws Exception;
}
