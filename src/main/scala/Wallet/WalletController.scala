package Wallet


import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.{PathVariable,  RequestMethod,RequestParam, RequestMapping,RequestBody,ResponseBody,ResponseStatus,RequestHeader}

import org.springframework.http.HttpStatus
import Resource.User
import Resource.IDCard
import Resource.WebLogin
import Resource.BankAccount

import org.springframework.stereotype.Controller
//import java.lang.Int
import org.springframework.web.bind.annotation.PathVariable
import javax.validation.Valid
import scala.collection.mutable.Map
import scala.collection.mutable.MultiMap
import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import java.util.concurrent.atomic.AtomicInteger;
//import org.joda.time.DateTime
//import org.joda.time.DateTime
 // import org.joda.time.format.DateTimeFormatter
 // import org.joda.time.format.DateTimeFormat

import java.util.Date

import java.text.SimpleDateFormat
//import java.time
import java.util.ArrayList
@EnableAutoConfiguration
@RestController
class  WalletConfig  ()  {

 var UserMap:Map[Int,User]= Map()
 var IDMap:Map[Int,ArrayList[IDCard]]= Map()
// var UserToIDMap:Map[Int,String]= Map()
 private final  var Usercounter: AtomicInteger = new AtomicInteger();
 private final  var IDcounter: AtomicInteger = new AtomicInteger();
private  final  var WebLogincounter: AtomicInteger = new AtomicInteger();
private  final  var BankAccountcounter: AtomicInteger = new AtomicInteger();

  val MultiMapID = new HashMap[Int, Set[IDCard]] with MultiMap[Int, IDCard]
  val MultiMapWebLogin = new HashMap[Int, Set[WebLogin]] with MultiMap[Int, WebLogin]
   val MultiMapBankAccount = new HashMap[Int, Set[BankAccount]] with MultiMap[Int, BankAccount]
 //------------------------------------USER---------------------------------------------------------------------------------------

 
@RequestMapping(value = Array("/users/{user_id}"),method = Array(RequestMethod.GET), produces = Array("application/json"))
@ResponseBody
def listUsers(@PathVariable("user_id") user_id:Int):User = {
  if (!UserMap.contains(user_id)) 
    {  var userError:User = new User()
      userError.setName ("User not registered"); return userError
    }
  else 
  return UserMap(user_id) 

}




 @RequestMapping(value = Array("/users") ,method = Array(RequestMethod.POST), consumes =
    Array("application/json") , produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def createUser(@RequestBody User: User): User = {
       

//val myDate = new DateTime()
 //DateTimeFormatter fmt = ISODateTimeFormat.dateTime()
  //println(myDate.toString(fmt))
 //var dtf:DateTimeFormatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
 //var jodatime:DateTime = dtf.parseDateTime(DateTime.now().toString);


    var df:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

       User.setUser_id ( Usercounter.incrementAndGet())
       User.setCreated_at( df.format(new Date()) )
       UserMap+=(User.getUser_id() -> User)
      
       return User
}



@RequestMapping(value = Array("/users/{user_id}") ,method = Array(RequestMethod.PUT), consumes =
    Array("application/json") , produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def updateUser(@RequestBody User: User,@PathVariable("user_id") user_id:Int): User = {
       
 if (!UserMap.contains(user_id)) 
    {  var userError:User = new User()
      userError.setName("User not registered"); return userError
    }

     else{  
       //val timestamp: Date = new Date()       
      // User.updated_at= timestamp
       var df:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       UserMap(user_id).setUpdated_at(df.format(new Date()))  


    if(User.getEmail()!=null) UserMap(user_id).setEmail(User.getEmail()); 
    if(User.getPassword()!=null) UserMap(user_id).setPassword(User.getPassword()); 
    if(User.getName()!=null) UserMap(user_id).setName(User.getName())
      
       
       return UserMap(user_id)
     }
}


//------------------------------------IDCARD---------------------------------------------------------------------------------------

@RequestMapping(value = Array("/users/{user_id}/idcards") ,method = Array(RequestMethod.POST), consumes =
    Array("application/json") , produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def createCard(@RequestBody IDCard: IDCard,@PathVariable("user_id") user_id:Int): IDCard = {
       


      IDCard.setCard_id ( IDcounter.incrementAndGet())
      MultiMapID.addBinding(user_id,IDCard)

       return IDCard
}




@RequestMapping(value = Array("/users/{user_id}/idcards"),method = Array(RequestMethod.GET), produces = Array("application/json"))
@ResponseBody
def listCards(@PathVariable("user_id") user_id:Int):ArrayList[IDCard] = {
 
    val IdCardsList: ArrayList[IDCard] = new ArrayList()

  for(Id<-MultiMapID(user_id))
    IdCardsList.add(Id)    

return IdCardsList

}


@RequestMapping(value = Array("/users/{user_id}/idcards/{card_id}"),method = Array(RequestMethod.DELETE), produces = Array("application/json"))
@ResponseStatus(HttpStatus.NO_CONTENT)
@ResponseBody
def deleteCard(@PathVariable("user_id") user_id:Int,@PathVariable("card_id") card_id:Int) = {
  for(Id<-MultiMapID(user_id))
   {


    if(Id.getCard_id() == card_id)
    {
    MultiMapID.removeBinding(user_id,Id)
   
    }
 
  }

}
//------------------------------------WEBLOGIN--------------------------------------------------------------------------------

@RequestMapping(value = Array("/users/{user_id}/weblogins") ,method = Array(RequestMethod.POST), consumes =
    Array("application/json") , produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def createWebLogin(@RequestBody WebLogin: WebLogin,@PathVariable("user_id") user_id:Int): WebLogin = {
       
      WebLogin.setLogin_id( WebLogincounter.incrementAndGet()) 
         MultiMapWebLogin.addBinding(user_id,WebLogin)
       return WebLogin
}


@RequestMapping(value = Array("/users/{user_id}/weblogins"),method = Array(RequestMethod.GET), produces = Array("application/json"))
@ResponseBody
def listWebLogins(@PathVariable("user_id") user_id:Int):ArrayList[WebLogin] = {
    val WebLoginList: ArrayList[WebLogin] = new ArrayList()

  for(Wlogin<-MultiMapWebLogin(user_id))
    WebLoginList.add(Wlogin)    

return WebLoginList

}




@RequestMapping(value = Array("/users/{user_id}/weblogins/{login_id}"),method = Array(RequestMethod.DELETE), produces = Array("application/json"))
@ResponseStatus(HttpStatus.NO_CONTENT)
@ResponseBody
def deleteWebLogin(@PathVariable("user_id") user_id:Int,@PathVariable("login_id") login_id:Int) = {

  for(WLogin<-MultiMapWebLogin(user_id))
   {

    if(WLogin.getLogin_id() == login_id)
    {
    MultiMapWebLogin.removeBinding(user_id,WLogin)

    }
 
  }

}




//------------------------------------BANK_ACCOUNT--------------------------------------------------------------------------------

@RequestMapping(value = Array("/users/{user_id}/bankaccounts") ,method = Array(RequestMethod.POST), consumes =
    Array("application/json") , produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def createBankAccount(@RequestBody BankAccount: BankAccount,@PathVariable("user_id") user_id:Int): BankAccount = {
       
      BankAccount.setBa_id ( BankAccountcounter.incrementAndGet())
         MultiMapBankAccount.addBinding(user_id,BankAccount)
       return BankAccount
}


@RequestMapping(value = Array("/users/{user_id}/bankaccounts"),method = Array(RequestMethod.GET), produces = Array("application/json"))
@ResponseBody
def listBankAccounts(@PathVariable("user_id") user_id:Int):ArrayList[BankAccount] = {
    val BankAccountList: ArrayList[BankAccount] = new ArrayList()

  for(BA<-MultiMapBankAccount(user_id))
    BankAccountList.add(BA)    

return BankAccountList

}



@RequestMapping(value = Array("/users/{user_id}/bankaccounts/{ba_id}"),method = Array(RequestMethod.DELETE), produces = Array("application/json"))
@ResponseStatus(HttpStatus.NO_CONTENT)
@ResponseBody
def deleteBankAccount(@PathVariable("user_id") user_id:Int,@PathVariable("ba_id") ba_id:Int) = {

  for(BA<-MultiMapBankAccount(user_id))
   {

    if(BA.getBa_id() == ba_id)
    {
    MultiMapBankAccount.removeBinding(user_id,BA)

    }
 
  }

}





}
