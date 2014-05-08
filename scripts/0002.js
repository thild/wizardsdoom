function choice1Accept(pc, npc) {
  var knight = sceneManager.getEntity(pc);
  knight.increaseSpeed(50);
}

function choice2Accept(pc, npc) {
  var knight = sceneManager.getEntity(pc);  
  knight.increaseHealth(10);
}

function choice3Accept(pc, npc) {
  var knight = sceneManager.getEntity(pc);  
  knight.increasePower(10);
}

function choice1Condition(pc, npc) {
}

function choice2Condition(pc, npc) {
}

function choice3Condition(pc, npc) {
}