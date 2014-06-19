function saySomething(something) {
  var knight = game.getEntity('knight');
  knight.speak(something);
}

function doSomeMath(a, b) {
  var knight = game.getEntity('knight');  
  knight.speak(a + b);
}


function jump() {
  var knight = game.getEntity('knight');  
  knight.setLocation(knight.getX() + 10, knight.getY());  
}