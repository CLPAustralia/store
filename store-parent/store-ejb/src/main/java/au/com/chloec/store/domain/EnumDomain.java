package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "enum_domain")
@Name("enumDomain")
@EqualsAndHashCode(callSuper=false,of={"id"})
@ToString
public class EnumDomain extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enum_domain_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "domain_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
