package Service;

import Dao.UserDAO;
import Model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try{
            if(UserDAO.isExists(user.getEmail())){
                return 1;
            }else{
                UserDAO.saveUser(user);
                        return 0;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
