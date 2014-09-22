package Wallet


import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan


import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod


import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.{PathVariable, ModelAttribute, RequestMethod, RequestMapping,RequestBody,ResponseStatus}
//import org.springframework.data.repository.CrudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.http.HttpStatus
import Resource.User

@EnableAutoConfiguration
@RestController
//@RequestMapping(value=Array("/"))
class WalletConfig {

 //@RequestMapping("/greeting")
@RequestMapping(value = Array("/"))
@ResponseBody
def greeting(): String = {
return "Hello World!!!!!!!!!!!!!"
}


/*
@RequestMapping(value = Array("/foo"),method=Array(RequestMethod.POST))
@ResponseBody
  def foo(@RequestParam  name:String): String = {
  	
return "FOOO!!!!!!!!!!!!!"+name
}*/



 @RequestMapping(value = Array("/foo") ,method = Array(RequestMethod.POST), consumes =
    Array("application/json") 
    //, produces = Array("application/json")
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    def create(@RequestBody User: User): String = {
       // return "FOOO!!!!!!!!!!!!!"+User.name//+User.email
       return User.name;
}
}
