# brain-print-engine

[regular expression 101](https://regex101.com/)

# ✅ Java Program: Match "3m 27s video"

Here's a Java program that matches the string **"3m 27s video"** using a regular expression and confirms whether it fits the expected format.

Copilot Prompt
```
3m 27s video write java program match
```

```
import java.util.regex.*;

public class MatchVideoDuration {
    public static void main(String[] args) {
        String input = "3m 27s video";
        String pattern = "^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$";

        boolean isMatch = Pattern.matches(pattern, input);

        if (isMatch) {
            System.out.println("✅ Match found for: " + input);
        } else {
            System.out.println("❌ No match for: " + input);
        }
    }
}

```

## 🔍 Explanation:
* Pattern.matches() checks if the entire string matches the regex.

* (?:...) is a non-capturing group for optional minutes.

* (\\d+)m\\s* matches minutes like 3m (optional).

* (\\d+)s matches seconds like 27s (required).

* video must follow with at least one space (\\s+).

## 🧪 Output:
```
✅ Match found for: 3m 27s video

```




















