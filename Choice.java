/**
 * Write a description of class Choice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Choice {
   
   private String message;
   private String conditionFunction;
   private String acceptFunction;
   private String id;
   
    public Choice() {
    
    }
   
    public String getId() {
        return id;
    }
   
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }    
    
    public String getConditionFunction() {
        return conditionFunction;
    }
    
    public void setConditionFunction(String conditionFunction) {
        this.conditionFunction = conditionFunction;
    }    
    
    public String getAcceptFunction() {
        return acceptFunction;
    }
    
    public void setAcceptFunction(String acceptFunction) {
        this.acceptFunction = acceptFunction;
    }    

}
