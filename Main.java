import java.security.SecureRandom;

public final class Main {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijkmnopqrstuvwxyz";
    private static final String DIGITS = "23456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";
    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;
    private Main() {}

    public static void main(String[] args) {
        int length = args.length == 0 ? 16 : parseLength(args[0]);
        if (length < 8 || length > 128) throw new IllegalArgumentException("Length must be between 8 and 128.");
        char[] password = new char[length];
        password[0] = pick(UPPER);
        password[1] = pick(LOWER);
        password[2] = pick(DIGITS);
        password[3] = pick(SYMBOLS);
        for (int i = 4; i < length; i++) password[i] = pick(ALL);
        for (int i = password.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = password[i]; password[i] = password[j]; password[j] = temp;
        }
        System.out.println(new String(password));
    }

    private static char pick(String alphabet) { return alphabet.charAt(RANDOM.nextInt(alphabet.length())); }
    private static int parseLength(String value) {
        try { return Integer.parseInt(value); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Length must be a whole number.", ex); }
    }
}
