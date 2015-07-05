package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("enumInstance")
@Table(name = "enum_instance")
@EqualsAndHashCode(callSuper=true)
public class EnumInstance extends AbstractDomainObjectWithId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private EnumDomain domain;

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
