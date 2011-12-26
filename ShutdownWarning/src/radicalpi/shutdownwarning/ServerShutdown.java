package radicalpi.shutdownwarning;

import org.bukkit.Server;

public class ServerShutdown implements Runnable {
	
	Server m_server;
	
	public ServerShutdown(Server svr) {
		m_server = svr;
	}

	@Override
	public void run() {
		m_server.shutdown();
	}

}
