package model;

import java.util.Objects;

public class CookingRoutine  {

    private String routine;

    CookingRoutine(String routine){
        this.routine = routine;
    }

    public String getRoutine(){
        return routine;
    }

    public void setRoutine(String routine){
        this.routine = routine;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookingRoutine)) return false;
        CookingRoutine that = (CookingRoutine) o;
        return Objects.equals(routine, that.routine);
    }

    @Override
    public int hashCode() {

        return Objects.hash(routine);
    }
}
