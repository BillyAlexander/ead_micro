package com.ead.authServer.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Table(name="TB_USERS_COURSES")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserCourseModel implements Serializable {
	private static final long serialVersionUID=1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private UserModel user;
	
	@Column(nullable = false)
	private UUID courseId;
	
	
}
