package GlobalTrendProgrammingAssessment;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        // Convert to lowercase and remove non-alphanumeric characters
        String normalizedStr = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        // Check if the string is a palindrome
        int left = 0;
        int right = normalizedStr.length() - 1;

        while (left < right) {
            if (normalizedStr.charAt(left) != normalizedStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "A man, a plan, a canal, Panama";
        System.out.println(str1 + " is a palindrome? " + isPalindrome(str1)); // Output: true

        String str2 = "race a car";
        System.out.println(str2 + " is a palindrome? " + isPalindrome(str2)); // Output: false
    }
}

