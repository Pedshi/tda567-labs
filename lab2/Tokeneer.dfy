datatype ClearanceLevel = Low | Medium | High;
//datatype Token = Token(id : int, clearance : ClearanceLevel);

class Token {
  var id : int;
  var clearanceLevel : ClearanceLevel;
  var valid : bool;
  
  constructor (i : int, level : ClearanceLevel)
  modifies this;
  ensures id == i;
  ensures clearanceLevel == level;
  ensures valid; 
  {
    id := i;
    clearanceLevel := level;
    valid := true;
  }
  
  method invalidate()
  modifies `valid;
  requires valid;
  ensures !valid;
  {
    valid := false;
  }
}

class EnrollmentStation {
  var users : map<int, Token>;
  
  constructor ()
  modifies this;
  ensures |users| == 0;
  {
    users := map[];
  }
  
  method registerUser(id : int, clearance : ClearanceLevel) returns (token : Token)
  modifies `users;
  requires id !in users;
  ensures id in users;
  ensures users[id] == token;
  ensures token != null;
  ensures token.id == id;
  {
    var tok := new Token(id, clearance); 
    users := users[id := tok];
    return (tok);
  }
}

class IDStation {
  var doorClearance : ClearanceLevel;
  
  constructor (level : ClearanceLevel)
  modifies this;
  ensures doorClearance == level;
  {
    doorClearance := level;
  }
  
  method authenticateToken(fingerprint : int, token : Token) returns (valid : bool)
  requires token != null;
  requires token.valid;
  ensures ((token.id == fingerprint) && (token.clearanceLevel == doorClearance)) <==> valid;  
  {
    if (token.id == fingerprint){
      return (token.clearanceLevel == doorClearance);
    } else {
      return false;
    }
  }
}

class SuperSystem {
  var enrlstn : EnrollmentStation;
  var idstn : IDStation;
  var doorOpen : bool;
  var alarmSounding : bool;
  
  constructor (clearance : ClearanceLevel)
  modifies this;
  ensures idstn != null;
  ensures enrlstn != null;
  ensures !doorOpen;
  ensures !alarmSounding;
  {
    enrlstn := new EnrollmentStation();
    idstn := new IDStation(clearance);
    doorOpen := false;
    alarmSounding := false;
  }
  
  method authenticate(fingerprint : int, token : Token)
  modifies token;
  modifies `doorOpen;
  modifies `alarmSounding;
  requires token != null;
  requires token.valid;
  requires idstn != null;
  requires enrlstn != null;
  ensures ((token.id == fingerprint) && (token.clearanceLevel == idstn.doorClearance)) ==> doorOpen;
  ensures !((token.id == fingerprint) && (token.clearanceLevel == idstn.doorClearance)) <==> alarmSounding && !token.valid;
  {
    var tmp := idstn.authenticateToken(fingerprint, token);
    if (tmp) {
      openDoor();
    } else {
      token.invalidate();
      raiseAlarm();
    }
  }
  
  method openDoor()
  modifies `doorOpen;
  ensures doorOpen;
  {
    print("Door open");
    doorOpen := true;
  }
  
  method raiseAlarm()
  modifies `alarmSounding;
  ensures alarmSounding;
  {
    print("BEEP BEEP BEEP");
    alarmSounding := true;
  }
  
  method Main(){
  }
}
