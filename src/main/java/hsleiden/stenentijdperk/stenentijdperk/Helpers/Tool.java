package hsleiden.stenentijdperk.stenentijdperk.Helpers;

import hsleiden.stenentijdperk.stenentijdperk.Interfaces.Status;

public class Tool implements Status {
    private int level;
    private boolean status;

    public Tool() {
        this.level = 1;
        this.status = true;
    }

    public void increaseLevel(){
        this.level += 1;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}