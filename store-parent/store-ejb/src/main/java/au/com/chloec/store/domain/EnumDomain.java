package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("enumDomain")
@Table(name = "enum_domain")
@EqualsAndHashCode(callSuper=true)
public class EnumDomain extends AbstractDomainObjectWithId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	@Column(name = "domain_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
