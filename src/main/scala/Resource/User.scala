package Wallet.Resource

//import javax.persistence.Id
//import javax.persistence.GeneratedValue
import java.lang.Long
//import javax.persistence.Entity
import scala.beans.BeanProperty
import org.hibernate.validator.constraints.NotEmpty

//@Entity
class User{
  
  //@Id 
 // @GeneratedValue 

@BeanProperty
  @NotEmpty
  var name: String = _


/*
@BeanProperty
  @NotEmpty
  var email: String = _



  @BeanProperty
  var user_id: Long = _
  
  
  
  @BeanProperty
  @NotEmpty
  var password : String = _
  
  


def name_=(_name:String) = this._name = _name  
  def name = this._name 

*/









/*
  @BeanProperty
  @NotEmpty
  var created_at: DateTime = _


  @BeanProperty
  @NotEmpty
  var updated_at: DateTime = _

*/
  
}