package com.ead.authServer.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.ead.authServer.enums.UserStatus;
import com.ead.authServer.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="TB_USERS")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserModel extends RepresentationModel<UserModel> implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false,unique = true, length = 50)
    private String userName;
    @Column(nullable = false,unique = true, length = 50)
    private String email;
    @Column(nullable = false,length = 255)
    @JsonIgnore
    private String password;
    @Column(nullable = false,length = 150)
    private String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 20)
    private String documentId;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdateDate;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserCourseModel> userCourses;
    
    public UserCourseModel convertToUserCourseModel(UUID courseId) {
    	return new UserCourseModel(null, this, courseId);
    }
    
}
