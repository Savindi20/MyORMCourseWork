package lk.ijse.hibernate.coursework.bo;

import lk.ijse.hibernate.coursework.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }

    public static BOFactory getBoFactory() {

        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
    public enum BOTypes{USER}
}
