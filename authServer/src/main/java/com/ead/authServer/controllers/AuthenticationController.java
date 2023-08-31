package com.ead.authServer.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authServer.configs.security.JwtProvider;
import com.ead.authServer.dtos.JwtDto;
import com.ead.authServer.dtos.LoginDto;
import com.ead.authServer.dtos.UserDto;
import com.ead.authServer.enums.RoleType;
import com.ead.authServer.enums.UserStatus;
import com.ead.authServer.enums.UserType;
import com.ead.authServer.models.RoleModel;
import com.ead.authServer.models.UserModel;
import com.ead.authServer.services.RoleService;
import com.ead.authServer.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;

	// Logger logger = LogManager.getLogger(AuthenticationController.class);

	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(
			@RequestBody @Validated(UserDto.UserView.RegistrationPost.class) @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

		log.debug("POST RECEIVED registerUser UserDto {}", userDto.toString());
		if (userService.existsByUserName(userDto.getUserName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: userName is already taken!.");
		}
		if (userService.existsByEmail(userDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: email is already taken!.");
		}

		RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_STUDENT)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));

		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setUserStatus(UserStatus.ACTIVE);
		userModel.setUserType(UserType.STUDENT);
		userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

		userModel.getRoles().add(roleModel);
		userService.saveUser(userModel);
		log.debug("POST SAVED registerUser getUserId {}", userModel.getUserId());
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwt(authentication);
		return ResponseEntity.ok(new JwtDto(jwt));		
	}

	@GetMapping("/")
	public String index() {
		log.trace("TRACE");
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");

		return "Loggin Spring";
	}
	
	@PostMapping("/signup/admin/user")
	public ResponseEntity<Object> registerUserAdmin(
			@RequestBody @Validated(UserDto.UserView.RegistrationPost.class) @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {

		log.debug("POST RECEIVED registerUser UserDto {}", userDto.toString());
		if (userService.existsByUserName(userDto.getUserName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: userName is already taken!.");
		}
		if (userService.existsByEmail(userDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: email is already taken!.");
		}

		RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found!"));

		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setUserStatus(UserStatus.ACTIVE);
		userModel.setUserType(UserType.ADMIN);
		userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

		userModel.getRoles().add(roleModel);
		userService.saveUser(userModel);
		log.debug("POST SAVED registerUser getUserId {}", userModel.getUserId());
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}

}
