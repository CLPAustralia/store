package au.com.chloec.store;

import javax.ejb.Local;

@Local
public interface Authenticator
{
   boolean authenticate();
}
