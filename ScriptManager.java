import javax.script.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;


/**
 * This class manages all scripts in the game.
 * See:
 * http://www.ibm.com/developerworks/java/library/j-5things9/index.html
 * http://www.coderanch.com/t/549722/java/java/Calling-Javascript-function-Java-Code
 * http://www.mytechnotes.biz/2013/04/calling-javascript-from-java-example.html
 * TODO: create a context for each script file or call a method from an object in javascript
 * http://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/
 * @author Tony Alexander Hild
 * @version 0.1a
 */
public final class ScriptManager  
{
    /**
     * We need only one script engine.
     */
    private static ScriptEngine engine;
    
    private static Map<String, Long> scriptModifiedStatus = new HashMap<String, Long>();

    //private static Bindings bindings;

    /**
     * Static constructor
     */
    static 
    {
        Thread.currentThread().setContextClassLoader(bluej.runtime.ExecServer.getCurrentClassLoader());
        engine = new ScriptEngineManager().getEngineByName("javascript");
        //Context.setApplicationClassLoader(bluej.runtime.ExecServer.getCurrentClassLoader());
        //int flags = DONTENUM | READONLY | PERMANENT;
        //ScriptableObject.defineProperty(prc, "Result", new NativeJavaClass(prc, Direction.class), flags);
        //engine.put("game", Game.getInstance()); //you can call this façade on script side.
        
        /*
        try {
            //engine.eval("importPackage(Packages);importPackage(Packages.greenfoot);");
            //bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        }
        
        catch(ScriptException scrEx)
        {
            scrEx.printStackTrace();
        } 
        */
    }
    
    private ScriptManager(){}

    /**
     * Evaluates an entire javascript file. Script files must be of type .js and must be located in "scripts" folder. 
     *
     * @param script the name of .js file without extension.
     */
    public static void evalFile(String script) {
        try{
            String file = "./scripts/" + script + ".js";
            if (Files.notExists(Paths.get(file))) return;
            FileReader fr = new FileReader(file);
            //engine.eval(fr, bindings);
            engine.eval(fr);
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
    
    /**
     * Evaluates a literal script.
     *
     * @param script the script to be evaluated.
     */
    public static void eval(String script) {
        try{
            //engine.eval(script, bindings);
            engine.eval(script);
        }
        catch(ScriptException scrEx)
        {
            scrEx.printStackTrace();
        }        
    }    
    

    /**
     * Invokes a function from a javascript file. Script files must be of type .js and must be located in "scripts" folder. 
     *
     * @param script the name of .js file without extension.
     * @param function the function to be called.
     * @param args the function arguments.
     */
    public static void invokeFunction(String script, String function,  Object... args) {
        try{
            String file = "./scripts/" + script + ".js";
            Path filePath = Paths.get(file);
            if (Files.notExists(filePath)) return;
            long lastModifiedTime = Files.getLastModifiedTime(filePath).toMillis();
            if(scriptModifiedStatus.get(script) == null) {
                scriptModifiedStatus.put(script, lastModifiedTime);
            }
            if(lastModifiedTime != scriptModifiedStatus.get(script)) {
                FileReader fr = new FileReader(file);
                //engine.eval(fr, bindings);
                engine.eval(fr);
            }
            
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
    
    /**
     * Invokes a function from a javascript file. Script files must be of type .js and must be located in "scripts" folder. 
     *
     * @param script the name of .js file without extension.
     * @param function the function to be called.
     * @param args the function arguments.
     */
    public static Object invokeMethod(String script, String object, String method,  Object... args) {
        try{
            String file = "./scripts/" + script + ".js";
            Path filePath = Paths.get(file);
            if (Files.notExists(filePath)) return null;
            long lastModifiedTime = Files.getLastModifiedTime(filePath).toMillis();
            if(scriptModifiedStatus.get(script) == null || lastModifiedTime != scriptModifiedStatus.get(script)) {
                scriptModifiedStatus.put(script, lastModifiedTime);
                FileReader fr = new FileReader(file);
                engine.eval(fr);
            }
            
            // get script object on which we want to implement the interface with
            Object obj = engine.get(object);
            if(obj == null) return null;
        
            // javax.script.Invocable is an optional interface.  
            // Check whether your script engine implements or not!  
            // Note that the JavaScript engine implements Invocable interface.  
            Invocable inv = (Invocable) engine;  
      
            // invoke the global function named "hello"  
            return inv.invokeMethod(obj, method, args);  
            
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
        return null;
    }
    
}
