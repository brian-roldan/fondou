package nz.co.fondou.domain;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

	@Id
	@GeneratedValue(strategy=AUTO)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(length=100, nullable=false)
	private String address;
	
	@Column(length=200, nullable=false)
	private String mapLink; 
	
}
