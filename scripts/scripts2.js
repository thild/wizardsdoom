//http://blogger.ziesemer.com/2007/10/respecting-javascript-global-namespace.html
//https://developer.mozilla.org/en-US/docs/Web/JavaScript/Introduction_to_Object-Oriented_JavaScript

var thief = new function(){
  this.touch = function(pc, npc) {
    //var pc = game.getEntity(pc);
    //var npc = game.getEntity(npc);
    pc.stopSpeak();
    npc.stopSpeak();
    pc.increaseMoney(-10);
    npc.increaseMoney(10);
    pc.speak("Ow no! I was robbed! Now I have $" + pc.getMoney());
    npc.speak("You lose playboy! You lose!");
    game.playSound("chaching");
    pc.freeze();
    npc.freeze();
  }
  
  this.interact = function(pc, npc) {
      //var pc = game.getEntity(pc);
      //var npc = game.getEntity(npc);
      npc.speak("You lose playboy!");
      pc.unfreeze();
      npc.unfreeze();
    }

  var clickCount = 0;  
    
  this.click = function(character) {
      //var character = game.getEntity(charName);
      print(clickCount);
      if (clickCount == 0) {
	character.speak("Leave me alone!");
	++clickCount;
      }
      else {
	character.stopSpeak();
	character.speak("Are you deaf!? Leave me alone!");
      }
  }

}();

var thief2 = new function(){
  this.touch = function(pc, npc) {
    //var pc = game.getEntity(pc);
    //var npc = game.getEntity(npc);
    pc.stopSpeak();
    npc.stopSpeak();
    pc.increaseMoney(10);
    npc.increaseMoney(-10);
    pc.speak("Ow no! I'm luck! Now I have $" + pc.getMoney());
    npc.speak("Wellcome!");
    game.playSound("chaching");
  }
  
}();

var choiceEvaluator = new function() {
    this.evaluate = function(choice) {
	choice.markAsChosen();
	game.playSound(choice.soundToPlay);
	if(choice.isClosingChoice())
	{
	  game.updateDialogueFeedbackMessage(choice.getFeedbackMessage);
	  dialogue.close();
	}
	if(choice.isRedirectingChoice()) {
	  game.redirectToDialogue(choice.redirectToDialogue, 
			          choice.getDialogue().getPc(), 
				  choice.getDialogue().getNpc());
	}	
    }
}();

var wizard = new function(){
  
  this.interact = function(pc, npc) {
    //pc.speak("Speaking to " + npc.getName() + ". Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet ullamcorper sapien vel lobortis.");
    //npc.speak("Speaking to " + pc.getName());
    //npc.increaseSpeed(20);
    //print("interacting");
    game.openDialogue('0001', pc, npc);
  }

  this.touch = function(pc, npc) {
    /*
    var pc = game.getEntity(pc);
    var npc = game.getEntity(npc);
    pc.stopSpeak();
    npc.stopSpeak();
    pc.increaseMoney(10);
    //wizard has infinite money
    //npc.increaseMoney(10);
    pc.speak("Nice! I won 10 bucks! Now I have $" + pc.getMoney());
    game.playSound("chaching");
    */
  }  
  
  this.evaluateDialogue = function(dialogue, choice) {
    switch(String(dialogue.getId())) {
      case '0001':

	
	/*
	switch(choice.getId()) {
	  case 0:
	    game.playSound("donkey");
	    game.openDialogue('0002', dialogue.pc, dialogue.npc);
	    break;
	  case 1:
	    game.updateDialogueMessage("");
	    break;
	}
	*/
	break;
      case '0002':
	switch(choice.getId()) {
	  case 0:
	    game.openDialogue('0001', dialogue.pc, dialogue.npc);
	    break;
	  case 1:
	    game.closeDialogue();
	    break;
	}
	break;
    }
  }  
  
  this.getDialogue = function(id) {
    switch(id) {
      case "0001":
	importClass(Packages.Dialogue);
	var d = new Dialogue();
	d.setId(id);
	d.setMessage("What's the capital of Turkey?");
	d.addChoice("Istambul", 
		    "Seems that you have missed geography classes. You loose 5 points of inteligence",
		    null, "donkey", true);
	d.addChoice("Ancara", "You're correct! You won 5 points of inteligence.",
		    null, "correct", true);
	return d;
      case '0002':
	importClass(Packages.Dialogue);
	var d = new Dialogue();
	d.setId(id);
	d.setMessage("Seems that you have missed geography classes.");
	d.addChoice("Try again?");
	d.addChoice("Back to school");
	return d;
    }
  }  
  
}();



/*
function touch(pc, npc) {
  var pc = game.getEntity(pc);
  var npc = game.getEntity(npc);
  pc.stopSpeak();
  npc.stopSpeak();
  pc.increaseMoney(-10);
  npc.increaseMoney(10);
  pc.speak("Ow no! I was robbed! I have now $" + pc.getMoney());
  print("\nOw no! I was robbed! I have now $" + pc.getMoney());
  npc.speak("You lose playboy!");
}
*/
