package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("enumInstance")
@Table(name = "enum_instance")
@EqualsAndHashCode(callSuper=true)
public class EnumInstance extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private EnumDomain domain;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enum_instance_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "instance_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "domain_id")
	public EnumDomain getDomain() {
		return domain;
	}

	public void setDomain(EnumDomain domain) {
		this.domain = domain;
	}

}
