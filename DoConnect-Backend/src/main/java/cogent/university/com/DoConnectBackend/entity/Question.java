package cogent.university.com.DoConnectBackend.entity;

import java.util.List;

public class Question {

	private int id;
	private String description_question;
	private String image_src;
	private String datetime;
	private String status;
	private String topic;
	private String title;
	
	//@OneToMany(mappedBy = "question", fetch=FetchType.EAGER)
	//@JsonIgnore
	private List<Answer> answers;
	
	// @OneToOne
	private String qcreated_by;
	//
	// @OneToOne
	private String qapproved_by;
	
	
	}


