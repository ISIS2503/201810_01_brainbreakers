//ID del wiring
  #include "WProgram.h"
void setup();
void loop();
void setColor(int redValue, int greenValue, int blueValue);
int id =  1;

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
}

void loop()
{
   val = digitalRead(inputPin);  // read input value

  if (val == HIGH) {            // check if the input is HIGH
    digitalWrite(ledPin, HIGH);  // turn LED ON
      Serial.println("Alerta1");
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
          Serial.println("Alerta2");
      
         
     }
  //If current key matches the key length
  if (currentKey.length()== KEY.length()+1) {
    if(currentKey == KEY+"#" && !open || currentKey == KEY2+"#" && !open ) {
      digitalWrite(10,HIGH);
      open = true;
      currTime = millis();
      setColor(255, 0, 255);
      attempts = 0;
    }
    else {
      attempts++;
      currentKey = "";
    }
  }
  if(attempts>=maxAttempts) {
    currentKey = "";
    attempts = 0;
    Serial.println("Alerta3");
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
        Serial.println("Alerta2");
      }
    }else{
      setColor(255, 255, 0);
      open = false;
      buttonState = false;
     
    }
  }
  
 
  delay(100);

}

//Method that outputs the RGB specified color
void setColor(int redValue, int greenValue, int blueValue) {
  analogWrite(R_LED_PIN, redValue);
  analogWrite(G_LED_PIN, greenValue);
  analogWrite(B_LED_PIN, blueValue);
}

