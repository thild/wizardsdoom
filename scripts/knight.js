function saySomething(something) {
  var knight = sceneManager.getEntity('knight');
  knight.speak(something);
}

function doSomeMath(a, b) {
  var knight = sceneManager.getEntity('knight');  
  knight.speak(a + b);
}


function jump() {
  var knight = sceneManager.getEntity('knight');  
  knight.setLocation(knight.getX() + 10, knight.getY());  
}