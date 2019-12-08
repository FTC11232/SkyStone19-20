package org.firstinspires.ftc.teamcode.Scotts_Things;

public class direction {
    String name;


    public direction(String init){
        this.name = init;
    }

    public direction(){
        //Constructor
    }

    public void setName(String set){
        this.name = set;
    }

    public String getDirection() {
        return this.name;
    }

    public void setDirection(direction input){
        this.name = input.getDirection();
    }
}
