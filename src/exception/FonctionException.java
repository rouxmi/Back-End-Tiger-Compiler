package exception;

public class FonctionException extends Exception {

    public FonctionException(String message) {
        super("\u001B[91mFonctionException: "+message+"\u001B[0m\n");
    }
}
