package cogent.university.com.DoConnectBackend.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answer_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description_answer;
	private String img_src;
	private String status;
	private String datetime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	//@OneToOne
	private String approved_by;
	
	//@OneToOne
	private String created_by;
}
