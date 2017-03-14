package khoavin.sillylearningenglish.SingleViewObject;

/**
 * Created by KhoaVin on 2/12/2017.
 */

public class Friend {
    public String Uid;
    public Friend(String avatar, String name, boolean online_Status) {
        Avatar = avatar;
        Name = name;
        Online_Status = online_Status;
    }

    public Friend(String uid, String avatar, String name, boolean online_Status) {
        Uid = uid;
        Avatar = avatar;
        Name = name;
        Online_Status = online_Status;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String Avatar;

    public boolean getOnline_Status() {
        return Online_Status;
    }

    public void setOnline_Status(boolean online_Status) {
        Online_Status = online_Status;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String Name;
    public boolean Online_Status;

}