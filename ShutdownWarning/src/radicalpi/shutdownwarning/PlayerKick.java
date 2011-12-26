package radicalpi.shutdownwarning;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerKick implements Runnable {
	
	Server m_server;
	int m_time;
	
	public PlayerKick(Server svr, int time) {
		m_server = svr;
		m_time = time;
	}

	@Override
	public void run() {
		if (m_time != 0) {
			m_server.broadcastMessage(ChatColor.RED + "All players will be kicked in " + m_time + " seconds!");
		} else {
			for (Player p : m_server.getOnlinePlayers()) {
				p.kickPlayer("Server is shutting down");
			}
		}
	}

}
