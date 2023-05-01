package cogent.university.com.DoConnectBackend.entity;

import javax.persistence.*;


@Entity
@Table(name = "answer_tbl")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description_answer;
	@Lob
	private String image_src;
	private String status;
	private String datetime;
	
	@ManyToOne()
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	//@OneToOne
	private String approved_by;
	
	//@OneToOne
	private String created_by;

	public Answer() {
	}

	public Answer(String description_answer, String image_src, String status, String datetime) {
		this.description_answer = description_answer;
		this.image_src = image_src;
		this.status = status;
		this.datetime = datetime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription_answer() {
		return description_answer;
	}

	public void setDescription_answer(String description_answer) {
		this.description_answer = description_answer;
	}

	public String getImage_src() {
		return image_src;
	}

	public void setImage_src(String image_src) {
		this.image_src = image_src;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
}
