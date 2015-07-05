package au.com.chloec.store;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import au.com.chloec.store.domain.User;

@Stateless
@Name("authenticator")
public class AuthenticatorAction 
    implements Authenticator
{
//    @PersistenceContext
	@In
    private EntityManager entityManager;

    @In(required=false)   
    @Out(required=false, scope = SESSION)
    private User user;
   
    public boolean authenticate()
    {
	List results = entityManager.createQuery("select u from User u where u.username=#{identity.username} and u.password=#{identity.password}")
                         .getResultList();
      
	if (results.size()==0) {
	    return false;
	} else {
	    user = (User) results.get(0);
	    return true;
	}
    }
    
}
