package session;

import javax.ejb.Stateful;

/**
 * Created by ZY on 2015/12/17.
 */
@Stateful(name = "LoginEJB")
public class LoginBean implements Login{
    public LoginBean() {
    }

    public boolean execute() {
        return false;
    }
}
