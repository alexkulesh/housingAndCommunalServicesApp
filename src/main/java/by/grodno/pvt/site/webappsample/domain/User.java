package by.grodno.pvt.site.webappsample.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private Date birthdate;

	private String avatarFileName;

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
