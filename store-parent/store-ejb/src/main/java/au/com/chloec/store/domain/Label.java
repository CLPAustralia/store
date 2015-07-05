package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("label")
@EqualsAndHashCode(callSuper=true)
public class Label extends AbstractDomainObjectWithId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	@Column(name = "label_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
