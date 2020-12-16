/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

/**
 *
 * @author tret
 */
public class Conf {
    private String user = "anonymous";
    
    public String readUser() {
        return user;
    }
    
    public void writeUser(String user) {
        this.user = user;
    }
}
