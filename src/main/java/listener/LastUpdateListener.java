package listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import entity.Message;

public class LastUpdateListener {
	
	@PrePersist
	@PreUpdate
	public void setLastUpdate(Object obj) {
		Message msg = null;
		if (obj != null && obj instanceof Message) {
			msg = (Message) obj;
			msg.setLastUpdate( LocalDateTime.now() );
		}
		System.out.println(
			"LastUpdateListener#setLastUpdate(Object) called. lastUpdate = " + msg.getLastUpdate()
		);
	}

}
