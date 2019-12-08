package org.firstinspires.ftc.teamcode.Scotts_Things;

public class mechanism {
    String name;

    public mechanism(String init){
        this.name = init;
    }

    public mechanism(){

    }

    public String getMechanism(){
        return name;
    }

    public void setMechanism(String set) {
        this.name = set;
    }

    public void setMechanism(mechanism setUp){
        setMechanism(setUp.getMechanism());
    }

}
