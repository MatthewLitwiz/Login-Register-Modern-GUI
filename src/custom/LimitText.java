package custom;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// this class will be used to limit the character input in the field
public class LimitText extends PlainDocument { // By extending PlainDocument, LimitText inherits its properties and methods, making it possible to customize the behavior of text handling.
    private int charLimit;

    public LimitText(int charLimit){this.charLimit = charLimit;}

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException { // It is used to insert a string into the document at the specified offset, with the specified attributes.
        if(str == null) return; // This line checks if the string str is null. If it is null, the method returns immediately without doing anything

        if((super.getLength() + str.length()) <= charLimit){ // This line checks if the length of the current document plus the length of the string to be inserted is less than or equal to the character limit (charLimit). If the combined length exceeds the limit, the string will not be inserted.
            super.insertString(offs, str, a); // If the length check passes, this line calls the insertString method of the superclass (PlainDocument) to perform the actual insertion of the string at the specified offset with the specified attributes.
        }
    }
}