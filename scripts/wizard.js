

var wizard = new function(){
  this.interact = function(pc, npc) {
    var pc = game.getEntity(pc);
    var npc = game.getEntity(npc);
    pc.speak("Speaking to " + npc.getName() + ". Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet ullamcorper sapien vel lobortis.");
    npc.speak("Speaking to " + pc.getName());
    npc.increaseSpeed(20);
  }

  this.touch = function(pc, npc) {
    var pc = game.getEntity(pc);
    var npc = game.getEntity(npc);
    pc.stopSpeak();
    npc.stopSpeak();
    pc.increaseMoney(10);
    //wizard has infinite money
    //npc.increaseMoney(10);
    pc.speak("Nice! I won 10 bucks! Now I have $" + pc.getMoney());
    game.playSound("chaching");
  }  
  
}();