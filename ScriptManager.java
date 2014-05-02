import javax.script.*;
import java.io.*;

/**
 * Write a description of class ScriptManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public final class ScriptManager  
{
    //http://www.ibm.com/developerworks/java/library/j-5things9/index.html
    //http://www.coderanch.com/t/549722/java/java/Calling-Javascript-function-Java-Code
    //http://www.mytechnotes.biz/2013/04/calling-javascript-from-java-example.html
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    private static Bindings bindings;

    /**
     * Constructor for objects of class ScriptManager
     */
    static 
    {
        bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("sceneManager", SceneManager.getInstance());
    }

    public static void evalFile(String scriptFile) {
        try{
            FileReader fr = new FileReader("./scripts/" + scriptFile);
            engine.eval(fr, bindings);
            fr.close();
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        catch(ScriptException scrEx)
        {
            scrEx.printStackTrace();
        }        
    }
    
    public static void eval(String script) {
        try{
            engine.eval(script, bindings);
        }
        catch(ScriptException scrEx)
        {
            scrEx.printStackTrace();
        }        
    }    
    

    public static void invokeFunction(String scriptFile, String function,  Object... args) {
        try{
            FileReader fr = new FileReader("./scripts/" + scriptFile);
            engine.eval(fr, bindings);
            
            // javax.script.Invocable is an optional interface.  
            // Check whether your script engine implements or not!  
            // Note that the JavaScript engine implements Invocable interface.  
            Invocable inv = (Invocable) engine;  
      
            // invoke the global function named "hello"  
            inv.invokeFunction(function, args);  
            
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        catch(ScriptException scrEx)
        {
            scrEx.printStackTrace();
        }        
        catch (NoSuchMethodException nsmEx) 
        {
            nsmEx.printStackTrace();
        }
    }
    
    
}
