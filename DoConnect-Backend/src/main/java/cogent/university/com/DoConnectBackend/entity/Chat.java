package cogent.university.com.DoConnectBackend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chat {
	
	private int id;
	private String from_user;
	private String to_user;
	private String message;
	private String datetime;
	

}
