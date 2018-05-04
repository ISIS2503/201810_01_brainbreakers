
  
  #include <EEPROM.h>
  
  
  //ID del wiring
    #include "WProgram.h"
void setup();
void loop();
void receiveData();
void processData();
boolean compareKey(String key);
void processCommand(String* result, String command);
void addPassword(int val, int index);
void updatePassword(int val, int index);
void deletePassword(int index);
void deleteAllPasswords();
void setColor(int redValue, int greenValue, int blueValue);
int id =  1;
    
  #define SIZE_BUFFER_DATA       100
    boolean     stringComplete = false;
    String      inputString = "";
    char        bufferData [SIZE_BUFFER_DATA];
  
  //Variable del ledRGB
  
    //R LED pin
    const int R_LED_PIN = 11;
  
    //G LED pin
    const int G_LED_PIN = 9;
  
    //B LED pin
    const int B_LED_PIN = 13;
  
  //Variable pin
  
    //Button pin
    const int CONTACT_PIN = 12;
    
    //Attribute that defines the button state
    boolean buttonState;
  
    //Current time when the button is tapped
    long currTime;
    
    //Buzzer pin
    int speakerPin = 17;
    
    
    //varibles de la bateria y anuncios de bateria baja
    
    //Minimum voltage required for an alert
     const double MIN_VOLTAGE = 0;
  
    //Battery indicator
    const int BATTERY_LED = 15;
  
    //Battery measure pin
    const int BATTERY_PIN = A0;
  
    //Current battery charge
    double batteryCharge;
  
  
    //Variables teclado
    #include <Keypad.h>
  
    //Specified password
    const String KEY = "1234";
    
    const String KEY2 = "4321";
  
    //Time in milliseconds which the system is locked
    const int LOCK_TIME = 30000;
  
    //Keypad rows
    const byte ROWS = 4; 
  
    //Keypad columns
    const byte COLS = 3;
  
    //Maximum number of attempts allowed
    const byte maxAttempts = 3;
  
    //Keypad mapping matrix
    char hexaKeys[ROWS][COLS] = {
      {
        '1', '2', '3'
      }
      ,
      {
        '4', '5', '6'
      }
      ,
      {
        '7', '8', '9'
      }
      ,
      {
        '*', '0', '#'
      }
    };
  
    //Keypad row pins definition
    byte rowPins[ROWS] = {
      8, 7, 6, 5
    }; 
  
    //Keypad column pins definition
    byte colPins[COLS] = {
      4, 3, 2
    };
  
    //Keypad library initialization
    Keypad customKeypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, ROWS, COLS); 
  
    //Current key variable
    String currentKey;
  
    //Door state
    boolean open;
    
    //Number of current attempts
    byte attempts;
    
    //If the number of current attempts exceeds the maximum allowed
    boolean block;
  
  //Sensor Pir
  
    int ledPin = 14;                // choose the pin for the LED
  
    int inputPin = 16;               // choose the input pin (for PIR sensor)
  
    int pirState = LOW;             // we start, assuming no motion detected
    
    int val = 0;                    // variable for reading the pin status
  
  
  void setup()
  {
     Serial.begin(9600);
     
    //teclado
    
    currentKey = "";
    open = false;
    attempts = 0;
    block = false;
    
    //led y boton
    buttonState = false;
    
    pinMode(R_LED_PIN, OUTPUT);
    pinMode(G_LED_PIN, OUTPUT);
    pinMode(B_LED_PIN, OUTPUT);
    pinMode(CONTACT_PIN,INPUT);
    
    setColor(255,255, 0);
    
    
    //pir
    
    pinMode(ledPin, OUTPUT);      // declare LED as output
    pinMode(inputPin, INPUT);     // declare sensor as input
    
    // bateria carga
    
    // Ouput pin definition for BATTERY_LED
    pinMode(BATTERY_LED,OUTPUT);
  
    //Input pin definition for battery measure
    pinMode(BATTERY_PIN,INPUT);
  }
  
  void loop()
  {
     val = digitalRead(inputPin);  // read input value
  
    if (val == HIGH) {            // check if the input is HIGH
      digitalWrite(ledPin, HIGH);  // turn LED ON
        Serial.println("alerta4");
        tone(speakerPin, 135);
        delay(500);           
        noTone(speakerPin);
    } else {
      digitalWrite(ledPin, LOW); // turn LED OFF
  
    }
    
    //Teclado
    char customKey;
  
    if(!block) {
      //Selected key parsed;
      customKey = customKeypad.getKey();
    }
    else {  
      while(true);
    }
  
    //Verification of input and appended value
    if (customKey) {  
      currentKey+=String(customKey);
    }
  
    //If the current key contains '*' and door is open
    if(open && currentKey.endsWith("*")) {
      open = false;
      setColor(255,255, 0);
      digitalWrite(10,LOW);
      currentKey = "";
    }
    //If the current key contains '#' reset attempt
    if(currentKey.endsWith("#")&&currentKey.length()<=KEY.length()) {
      currentKey = "";
    }
  
      if(open && (millis()-currTime)>=15000)
       {
            setColor(0, 255, 255);
            Serial.println("alerta2");
            tone(speakerPin, 135);
            delay(200);           
            noTone(speakerPin);
        
           
       }
    //If current key matches the key length
    if (currentKey.length()== KEY.length()) {
      if(compareKey(currentKey) && !open ) {
        digitalWrite(10,HIGH);
        open = true;
        currTime = millis();
        setColor(255, 0, 255);
        tone(speakerPin, 400);
        delay(1000);           
        noTone(speakerPin);
        attempts = 0;
      }
      else {
        attempts++;
        currentKey = "";
        if(attempts > 0)
        {
          tone(speakerPin, 135);
          delay(1000);           
          noTone(speakerPin);
        }
        
      }   
    }
    if(attempts>=maxAttempts) {
      currentKey = "";
      attempts = 0;
      Serial.println("alerta3");
       setColor(0, 255, 255);
      delay(LOCK_TIME);
       setColor(255,255, 0);
    }
   
    
    if(!buttonState) {
      if(digitalRead(CONTACT_PIN)) {
        currTime = millis();
        buttonState = true;
        setColor(255, 0, 255);
        open = true;
        attempts = 0;
  
      }
    }
    else {
      if(digitalRead(CONTACT_PIN)) {
        if((millis()-currTime)>=10000) {
          setColor(0, 255, 255);
          tone(speakerPin, 135);
          delay(1000);           
          noTone(speakerPin);
          Serial.println("alerta2");
        }
      }else{
        setColor(255, 255, 0);
        open = false;
        buttonState = false;
       
      }
    }
    
    // bateria baja 
    
    //Value conversion from digital to voltage
    batteryCharge = (analogRead(BATTERY_PIN)*5.4)/1024;
    
    //Measured value comparison with min voltage required
    if(batteryCharge<=MIN_VOLTAGE) {
      digitalWrite(BATTERY_LED,HIGH);
      Serial.println("alerta1");
      tone(speakerPin, 135);
      delay(400);           
      noTone(speakerPin);
    }
    else {
      digitalWrite(BATTERY_LED,LOW);
    }
    
  
    receiveData();
    processData();
    
    delay(100);
  }
  
void receiveData() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      inputString.toCharArray(bufferData, SIZE_BUFFER_DATA);
      stringComplete = true;
    }
  }
}
   
  void processData() {
    if (stringComplete) {
      // Implementaci\u00f3n...
      String arreglo [3];
      int index, val;
      
      processCommand(arreglo, inputString);
      val = arreglo[1].toInt();
      index = arreglo[2].toInt();
      
      
      
      
      if (arreglo[0] == "agregarClave")
      {
        addPassword(val, index);
      }
      else if ( arreglo[0] == "actualizarClave")
      {
        updatePassword(val, index);
      }
      else if ( arreglo[0] == "borrarClave")
      {
        deletePassword(index);
      }
      else if ( arreglo[0] == "borrarTodo")
      {
        deleteAllPasswords();
      }
      
      
      
      
      
      inputString = "";
      stringComplete = false;
    }
  }
  
  // Method that compares a key with stored keys
  boolean compareKey(String key) {
    int acc = 3;
    int codif, arg0, arg1; 
    for(int i=0; i<3; i++) {
      codif = EEPROM.read(i);
      while(codif!=0) {
        if(codif%2==1) {
          arg0 = EEPROM.read(acc);
          arg1 = EEPROM.read(acc+1)*256;
          arg1+= arg0;
          if(String(arg1)==key) {
            return true;
          }
        }
        acc+=2;
        codif>>=1;
      }
      acc=(i+1)*16+3;
    }
    return false;
  }
  
  
// Methods that divides the command by parameters
void processCommand(String* result, String command) {
  int i = 0;
  char* token;
  char buf[command.length() + 1];
  
  command.toCharArray(buf, sizeof(buf));
  token = strtok(buf, ";");
  
  while(token != NULL) {
    result[i++] = token;
    token = strtok(NULL, ";");
  }  
}

  
  //Method that adds a password in the specified index
  void addPassword(int val, int index) {
    byte arg0 = val%256;
    byte arg1 = val/256;
    EEPROM.write((index*2)+3,arg0);
    EEPROM.write((index*2)+4,arg1);
    byte i = 1;
    byte location = index/8;
    byte position = index%8;
    i<<=position;
    byte j = EEPROM.read(location);
    j |= i;
    EEPROM.write(location,j);
  }
  
  //Method that updates a password in the specified index
  void updatePassword(int val, int index) {
    byte arg0 = val%256;
    byte arg1 = val/256;
    EEPROM.write((index*2)+3,arg0);
    EEPROM.write((index*2)+4,arg1);
  }
  
  //Method that deletes a password in the specified index
  void deletePassword(int index) {
    byte i = 1;
    byte location = index/8;
    byte position = index%8;
    i<<=position;
    byte j = EEPROM.read(location);
    j ^= i;
    EEPROM.write(location,j);
  }
  
  //Method that deletes all passwords
  void deleteAllPasswords() {
    //Password reference to inactive
    EEPROM.write(0,0);
    EEPROM.write(1,0);
    EEPROM.write(2,0);
  }
  
  

  
  //Method that outputs the RGB specified color
  void setColor(int redValue, int greenValue, int blueValue) {
    analogWrite(R_LED_PIN, redValue);
    analogWrite(G_LED_PIN, greenValue);
    analogWrite(B_LED_PIN, blueValue);
  }

