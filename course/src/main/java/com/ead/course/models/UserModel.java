package com.ead.course.models;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import com.ead.course.enums.UserStatus;
import com.ead.course.enums.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	@Id
    private UUID userId;

	@Column(nullable = false,unique = true, length = 50)
    private String email;
	
	@Column(nullable = false,length = 150)
    private String fullName;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    
    @Column(length = 20)
    private String documentId;
    @Column
    private String imageUrl;
    
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CourseModel> courses;
	
}
