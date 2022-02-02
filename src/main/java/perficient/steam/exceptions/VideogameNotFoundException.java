package perficient.steam.exceptions;

public class VideogameNotFoundException extends RuntimeException{
    public VideogameNotFoundException(String message) {
        super(message);
    }
}
