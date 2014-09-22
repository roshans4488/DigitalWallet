package Wallet.Resource

//import javax.persistence.Id
//import javax.persistence.GeneratedValue
import java.lang.Long
//import javax.persistence.Entity
import scala.beans.BeanProperty
import org.hibernate.validator.constraints.NotEmpty

//@Entity
class IDCard{
  
  //@Id 
 // @GeneratedValue 
  @BeanProperty 
  var card_id: Integer = _
  
  @BeanProperty
  @NotEmpty
  var card_name: String = _
  
  @BeanProperty
  @NotEmpty
  var card_number: String = _
  
  

/*
  @BeanProperty
  @NotEmpty

  var expiration_date: DateTime = _


  
*/
  
}