package Model.CreateModel;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserSignUp {

    public int Id;
    public String UserName;
    public String Password;
    public String FirstName;
    public String LastName;
    public String Phone;
    public String Email;
    public boolean IsActive;
    public Date CreateAt;
}
