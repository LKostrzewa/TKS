package pl.lodz.p.it.tks.data;

public class ClientEnt extends UserEnt {

    private ClientTypeEnt type;
    //private String typeName;

    public ClientEnt(){
        type = new NormalClientEnt();
    }

    public ClientEnt(String login, String password, String name, String surname, ClientTypeEnt type) {
        super(login, password, name, surname);
        this.type = type;
    }

    @Override
    public String toString() {
        String tmp;
        if(isActive()){
            tmp = "is active";
        }
        else tmp = "is inactive";
        return "Client login " + getLogin() + " full name " + getName()
                + " " + getSurname() + " " + getType() + " type " + tmp;
        /*return "Client{" +
                "login='" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", type=" + type + '\'' +
                ", isActive='" + isActive() + '\'' +
                '}';*/
    }

    public ClientTypeEnt getType() {
        return type;
    }

    public void setType(ClientTypeEnt type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
