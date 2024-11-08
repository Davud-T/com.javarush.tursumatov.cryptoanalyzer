public class Encryptor {
    public final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public char shiftChar(char c, int key) {
        int index = getIndexInAlphabet(c);
        if (index != -1) {
            int shiftedIndex = (index + key + ALPHABET.length) % ALPHABET.length;
            return ALPHABET[shiftedIndex];
        } else {
            return c;
        }
    }

    public int getIndexInAlphabet(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
