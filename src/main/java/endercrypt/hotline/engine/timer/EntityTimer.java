package endercrypt.hotline.engine.timer;


import java.io.Serializable;


public interface EntityTimer extends Serializable
{
	public void call() throws Exception;
}
