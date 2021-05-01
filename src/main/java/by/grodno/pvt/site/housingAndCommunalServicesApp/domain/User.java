package by.grodno.pvt.site.housingAndCommunalServicesApp.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;

	private String lastName;

	private String phoneNumber;


	@Email
	@Column(unique = true)
	private String email;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date birthDate;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_table_requests", joinColumns = {
			@JoinColumn(name = "r1") }, inverseJoinColumns = {
			@JoinColumn(name = "r2") })
	private RequestForm requestForms;

	private UserRole role;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name = "u1"),
			inverseJoinColumns = @JoinColumn(name = "u2"))
	private List<Credentials> credentials;
	@Override
	public String toString() {
		return "" + id + "";
	}
}
