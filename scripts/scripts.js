//http://wtfjs.com/
//http://dmitrysoshnikov.com/ecmascript/chapter-7-2-oop-ecmascript-implementation/#prototype
//http://blogger.ziesemer.com/2007/10/respecting-javascript-global-namespace.html
//https://developer.mozilla.org/en-US/docs/Web/JavaScript/Introduction_to_Object-Oriented_JavaScript

var bootstrap = new function() {
  this.createGame = function() {
    importClass(Packages.Scene);
    importClass(Packages.NonPlayerCharacter);
    importClass(Packages.Wizard);
    importClass(Packages.Knight);
    
    var scene = new Scene("village");
    scene.setSoundToPlay("outside");
    game.addScene(scene);
    
    var w1 = new Wizard("wizard");
    w1.setLocation(300, 150);
    scene.addEntity(w1);
    
    var l1 = new Knight("knight");
    l1.setLocation(420, 300);
    scene.addEntity(l1);

    game.setScene(scene);
    game.setPc(l1);
    
    var thief = new NonPlayerCharacter("thief");
    thief.setSprite(3); //initialSprite
    thief.setMoveRadius(0); //radius of movement
    thief.setSouthSprites(3, 5);
    thief.setWestSprites(15, 17);
    thief.setEastSprites(27, 29);
    thief.setNorthSprites(39, 41);
    thief.setLocation(100, 400);
    thief.setCharacterImage("thief.jpg");
    scene.addEntity(thief);
    
    scene.setBackground("bg.png");
    
    var north = new Scene("north village");
    north.setBackground("north.png");
    scene.setNeighbourScene(Direction.NORTH, north);    
    north.addEntity(l1);
    game.addScene(north);
    
    thief = new NonPlayerCharacter("thief2");
    thief.setSprite(3); //initialSprite
    thief.setMoveRadius(0); //radius of movement
    thief.setSouthSprites(3, 5);
    thief.setWestSprites(15, 17);
    thief.setEastSprites(27, 29);
    thief.setNorthSprites(39, 41);
    thief.setLocation(100, 400);
    thief.setCharacterImage("thief.jpg");

    north.addEntity(thief);
  }
  
}();

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

var inventory = new function() {
  
  this.use = function(item) {
      inventoryItemManager.getUseFunction(item)();
  }
  
  this.addItem = function(item, useFunction) {
      game.addInventoryItem(item);
      inventoryItemManager.addUseFunction(item, useFunction);
  }
  
}();

var wizard = new function(){
  
  this.interact = function(player, me) {
    if(player.hasQueuedDialogue(me)) {
      
    }
    else {
      player.queueDialogue('0001', me);
    }
  }

  
  this.evaluateDialogue = function(dialogue, choice) {
	choiceEvaluator.evaluate(choice);
  }  
  
  
  
  this.getDialogue = function(id, player, interlocutor) {
    importClass(Packages.Choice);
    importClass(Packages.ExitChoice);
    importClass(Packages.HintChoice);
    importClass(Packages.AnswerChoice);
    importClass(Packages.RedirectChoice);
    importClass(Packages.Dialogue);
    importClass(Packages.InventoryItem);
    switch(id) {
      case "0001":
	var d = new Dialogue();
	d.setId(id);
	d.setPlayer(player);
	d.setInterlocutor(interlocutor);
	d.setSoundToPlay("dialogue");
	d.setMessage("What's the capital of Turkey?");
	//http://stackoverflow.com/questions/2842079/how-can-i-pass-a-javascript-function-to-a-java-method-to-act-as-a-callback-rhin
	//https://groups.google.com/forum/#!topic/netscape.public.mozilla.jseng/9f-P6N5VqCc
	//http://stackoverflow.com/questions/23328448/invoke-javascript-callback-from-a-java-method-asynchronously-using-rhino
	
	var c1 = new AnswerChoice("Istanbul");
	d.addChoice(c1);
	choiceManager.addChoseFunction(c1, function() {
	  println(c1.getMessage());
	  if(c1.wasChosen()) {
	    game.exitDialogue();
	    return;
	  }	  
	  d.setFeedbackMessage("Seems you missed some geography lessons. You loose 5 points of inteligence.");
	  d.getPlayer().increaseInteligence(-5);
	  d.close();
	  c1.markAsChosen();
	  game.playSound("wrong");
	  game.updateDialogueMessages();
	  game.enableDialogueChoices(false);
	});

	var c2 = new AnswerChoice("Rio de Janeiro");
	d.addChoice(c2);
	choiceManager.addChoseFunction(c2, function() {
	  println(c2.getMessage());
	  if(c2.wasChosen()) {
	    game.exitDialogue();
	    return;
	  }	  
	  d.setFeedbackMessage("Really!? You loose 7 points of inteligence.");
	  d.getPlayer().increaseInteligence(-7);
	  d.close();
	  c2.markAsChosen();
	  game.playSound("wrong");
	  game.updateDialogueMessages();
	  game.enableDialogueChoices(false);
	});
	
	var c3 = new AnswerChoice("Ankara");
	d.addChoice(c3);
	choiceManager.addChoseFunction(c3, function() {
	  println(c3.getMessage());
	  if(c3.wasChosen()) {
	    game.exitDialogue();
	    return;
	  }	  
	  d.setFeedbackMessage("You're correct! You won 5 points of inteligence.");
	  d.getPlayer().increaseInteligence(5);
	  d.close();
	  c3.markAsChosen();
	  game.playSound("correct");
	  game.updateDialogueMessages();
	  game.enableDialogueChoices(false);
	  var item = new InventoryItem();
	  item.setName("smartpotion");
	  item.setDescription("Smart potion");
	  inventory.addItem(item, function() {
	    d.getPlayer().increaseInteligence(5);
	  });
	});

	var c4 = new HintChoice("Please, give me a hint!");
	d.addChoice(c4);
	choiceManager.addChoseFunction(c4, function() {
	  println(c4.getMessage());
	  d.setFeedbackMessage("Is not the famous one.");
	  c4.markAsChosen();
	  game.updateDialogueMessages();
	});
	
	var c5 = new ExitChoice("Never mind!");
	d.addChoice(c5);
	choiceManager.addChoseFunction(c5, function() {
	  println(c5.getMessage());
	  if(c5.wasChosen()) {
	    game.exitDialogue();
	    return;
	  }	  
	  d.setFeedbackMessage("Come back when you know de answer.");
	  c5.markAsChosen();
	  game.updateDialogueMessages();
	});
	
	var c6 = new RedirectChoice("I don't know the correct answer. Please, give me an easier question!");
	d.addChoice(c6);
	choiceManager.addChoseFunction(c6, function() {
	  c6.markAsChosen();
	  var rd = game.redirectToDialogue("0002", player, interlocutor);
	  if(rd.isClosed()) {
	    d.setFeedbackMessage("You already got your chance!");
	    game.updateDialogueMessages();
	  }
	});
	
	return d;
	
      case '0002':
	var d = new Dialogue();
	d.setId(id);
	d.setPlayer(player);
	d.setInterlocutor(interlocutor);
	d.setSoundToPlay("dialogue");
	d.setMessage("What is the color of Napoleon's white horse??");
	
	var c1 = new AnswerChoice("Black");
	d.addChoice(c1);
	choiceManager.addChoseFunction(c1, function() {
	  if(c1.wasChosen()) {
	    game.redirectToDialogue("0001", player, interlocutor);
	    return;
	  }	  
	  d.setFeedbackMessage("Oow. You loose 10 points of inteligence.");
	  d.getPlayer().increaseInteligence(-10);
	  d.close();
	  c1.markAsChosen();
	  game.playSound("wrong");
	  game.updateDialogueMessages();
	  game.enableDialogueChoices(false);
	});

	var c2 = new AnswerChoice("White");
	d.addChoice(c2);
	choiceManager.addChoseFunction(c2, function() {
	  if(c2.wasChosen()) {
	    game.redirectToDialogue("0001", player, interlocutor);
	    return;
	  }	  
	  d.setFeedbackMessage("You own 1 point of inteligence.");
	  d.getPlayer().increaseInteligence(1);
	  d.close();
	  c2.markAsChosen();
	  game.playSound("correct");
	  game.updateDialogueMessages();
	  game.enableDialogueChoices(false);
	});

	var c3 = new RedirectChoice("Never mind!");
	d.addChoice(c3);
	choiceManager.addChoseFunction(c3, function() {
	  if(c3.wasChosen()) {
	    game.redirectToDialogue("0001", player, interlocutor);
	    return;
	  }	  
	  d.setFeedbackMessage("Come back when you know de answer.");
	  c3.markAsChosen();
	  game.updateDialogueMessages();
	});
	
	
	return d;
    }
  }  
  
}();








//do not chance this functions
var choiceEvaluator = new function() {
    this.evaluate = function(choice) {
	choiceManager.getChoseFunction(choice)();
    }
}();

var choiceManager = new function() {
    var map = {};
    this.getChoseFunction = function(choice) {
      return map[String(choice.getDialogue().getId())][choice.getId()];
    }
    this.addChoseFunction = function(choice, choseFunction) {
      var choices;
      var dialogue = String(choice.getDialogue().getId());
      if(map[dialogue] == undefined) {
	choices = new Array();
	map[dialogue] = choices;
      }
      else {
	choices = map[dialogue];
      }
      choices[choice.getId()] = choseFunction;
    }
}();

var inventoryItemManager = new function() {
    var map = {};
    this.getUseFunction = function(item) {
      return map[String(item.getName())];
    }
    this.addUseFunction = function(item, useFunction) {
      map[item.getName()] = useFunction;
    }
}();
