
package com.examly.springapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

// import org.hibernate.engine.jdbc.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;

	@NotNull(message = "Email is required")
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	@Column(name="email")
	private String email;

	@NotBlank(message = "Password is required")
	@NotNull(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	@Column(name="password")
	private String password;

    // @NotBlank(message = "Username is required")
	// @NotNull(message = "Username is required")
	// @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	@Column(name="username")
	private String username;

    @NotBlank(message = "Mobile number is required")
	@NotNull(message = "Mobile number is required")
	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	@Column(name="mobile_number")
	private String mobileNumber;


	@Column(name = "user_role")
	private String userRole;
	// @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	//  @JsonManagedReference
	// private List<Order> orders;
	// @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	//  @JsonManagedReference
	// private List<Review> reviews;
}

