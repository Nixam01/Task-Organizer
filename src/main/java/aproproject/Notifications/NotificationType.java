package aproproject.Notifications;

public enum NotificationType {
    DEADLINE("deadline"), CASUAL("casual"), WARNING("warning");

    public String scheme;

    NotificationType(String scheme){
        this.scheme = scheme;
    }

    public String getScheme(){
        return scheme;
    }

    @Override
    public String toString() {
        return scheme;
    }
}
