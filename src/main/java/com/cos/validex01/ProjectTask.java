package com.cos.validex01;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max =10, message = "Summary cannot exceed the length")
	@NotBlank(message = "Summary cannot be blank")
	private String summary;
	@NotBlank(message = "AcceptanceCriteria cannot be blank")
	private String acceptanceCriteria;
	private String status;
	@Email(message = "Your email xxx")
	private String email;
}


