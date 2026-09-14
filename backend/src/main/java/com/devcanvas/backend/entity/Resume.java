package com.devcanvas.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String jobTitle;

	@Column(nullable = false)
	private String experience;

	@Column(nullable = false)
	private String skills;

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String filePath;

	@Column(nullable = false)
	private Boolean publicResume = false;

	@Column(nullable = false)
	private LocalDateTime uploadedAt;

	@PrePersist
	protected void onCreate() {

		uploadedAt = LocalDateTime.now();

		if (publicResume == null) {
			publicResume = false;
		}
	}
}